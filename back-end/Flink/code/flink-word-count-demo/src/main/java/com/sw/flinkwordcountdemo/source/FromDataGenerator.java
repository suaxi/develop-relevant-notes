package com.sw.flinkwordcountdemo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/7 22:54
 */
public class FromDataGenerator {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataGeneratorSource<String> data = new DataGeneratorSource<>(
                (GeneratorFunction<Long, String>) value -> "index-" + value,
                Long.MAX_VALUE,
                RateLimiterStrategy.perSecond(2),
                Types.STRING
        );
        env.fromSource(data, WatermarkStrategy.noWatermarks(), "data-generator").print();
        env.execute();
    }
}
