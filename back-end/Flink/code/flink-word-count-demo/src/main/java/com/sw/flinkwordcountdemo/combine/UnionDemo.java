package com.sw.flinkwordcountdemo.combine;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/14 21:34
 */
public class UnionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> data1 = env.fromElements("a", "b", "c");
        DataStreamSource<Integer> data2 = env.fromElements(1, 2, 3);
        data1.union(data2.map(String::valueOf)).print();
        env.execute();
    }
}
