package com.sw.flinkwordcountdemo.sink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSink;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

/**
 * @author：suaxi
 * @date：2026/11/16 22:44
 */
public class SinkMQDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        // 开启checkpoint
        env.enableCheckpointing(300, CheckpointingMode.EXACTLY_ONCE);

        DataGeneratorSource<String> data = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "index-" + value,
                10,
                RateLimiterStrategy.perSecond(1),
                Types.STRING
        );

        DataStreamSource<String> ds = env.fromSource(data, WatermarkStrategy.noWatermarks(), "data-generator");
        RMQConnectionConfig rmqConnectionConfig = new RMQConnectionConfig.Builder()
                .setHost("192.168.123.88")
                .setPort(5672)
                .setUserName("admin")
                .setPassword("123456")
                .setVirtualHost("/")
                .build();
        ds.addSink(new RMQSink<>(rmqConnectionConfig, "FINK_TO_MQ", new SimpleStringSchema()));
        env.execute();
    }
}
