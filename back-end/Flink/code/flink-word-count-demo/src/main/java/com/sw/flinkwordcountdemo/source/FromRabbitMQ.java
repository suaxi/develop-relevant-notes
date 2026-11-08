package com.sw.flinkwordcountdemo.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

/**
 * @author：suaxi
 * @date：2026/11/7 22:03
 */
public class FromRabbitMQ {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        RMQConnectionConfig rmqConnectionConfig = new RMQConnectionConfig.Builder()
                .setHost("localhost")
                .setPort(5672)
                .build();
        DataStreamSource<?> ds = env.addSource(
                new RMQSource<>(
                        rmqConnectionConfig,
                        "FROM-QMQP-DEMO",
                        true,
                        new SimpleStringSchema()
                )).setParallelism(1);
        ds.print();
        env.execute();
    }
}
