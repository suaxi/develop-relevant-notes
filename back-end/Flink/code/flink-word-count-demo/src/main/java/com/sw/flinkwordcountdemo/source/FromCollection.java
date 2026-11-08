package com.sw.flinkwordcountdemo.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

/**
 * @author：suaxi
 * @date：2026/11/7 21:50
 */
public class FromCollection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        List<Integer> data = List.of(1, 2, 3, 4, 5);
        DataStreamSource<Integer> ds = env.fromCollection(data);
        ds.print();
        env.execute();

        // 8> 3
        // 9> 4
        // 6> 1
        // 7> 2
        // 10> 5

    }
}
