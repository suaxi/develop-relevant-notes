package com.sw.flinkwordcountdemo.sink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringEncoder;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.configuration.MemorySize;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZoneId;

/**
 * @author：suaxi
 * @date：2026/11/16 21:49
 */
public class FileSinkDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        // 开启checkpoint
        env.enableCheckpointing(300, CheckpointingMode.EXACTLY_ONCE);

        DataGeneratorSource<String> data = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "index-" + value,
                1000,
                RateLimiterStrategy.perSecond(1),
                Types.STRING
        );

        DataStreamSource<String> ds = env.fromSource(data, WatermarkStrategy.noWatermarks(), "data-generator");

        FileSink<String> fileSink = FileSink
                .<String>forRowFormat(new Path("D:/sandbox"), new SimpleStringEncoder<>(StandardCharsets.UTF_8.name()))
                .withOutputFileConfig(OutputFileConfig.builder()
                        .withPartSuffix(".log")
                        .build())
                // 按目录分桶
                .withBucketAssigner(new DateTimeBucketAssigner<>("yyyyMMddHH", ZoneId.systemDefault()))
                // 文件滚动策略
                .withRollingPolicy(DefaultRollingPolicy.builder()
                        .withInactivityInterval(Duration.ofHours(1))
                        .withMaxPartSize(new MemorySize(1024))
                        .build())
                .build();

        ds.sinkTo(fileSink);
        env.execute();
    }
}
