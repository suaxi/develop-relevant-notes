package com.sw.flinkwordcountdemo.combine;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * @author：suaxi
 * @date：2026/11/14 21:43
 */
public class ConnectDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<String> data1 = env.fromElements("a", "b", "c");
        DataStreamSource<Integer> data2 = env.fromElements(1, 2, 3);

        ConnectedStreams<String, Integer> connect = data1.connect(data2);
        SingleOutputStreamOperator<Object> map = connect.map(new CoMapFunction<String, Integer, Object>() {
            @Override
            public Object map1(String value) throws Exception {
                // 只处理流1
                return "stream-1: " + value;
            }

            @Override
            public Object map2(Integer value) throws Exception {
                // 只处理流2
                return "stream-2: " + value;
            }
        });
        map.print();
        env.execute();

        // stream-1: a
        // stream-2: 1
        // stream-1: b
        // stream-2: 2
        // stream-1: c
        // stream-2: 3
    }
}
