package com.sw.flinkwordcountdemo.combine;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：suaxi
 * @date：2026/11/14 21:50
 */
public class CoProcessFunctionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Tuple2<Integer, String>> data1 = env.fromElements(
                Tuple2.of(1, "乌萨奇"),
                Tuple2.of(2, "孙笑川"),
                Tuple2.of(1, "小八"),
                Tuple2.of(2, "药水哥")
        );
        DataStreamSource<Tuple3<Integer, String, String>> data2 = env.fromElements(
                Tuple3.of(1, "乌萨奇", "吉伊卡哇"),
                Tuple3.of(2, "孙笑川", "6324"),
                Tuple3.of(1, "小八", "吉伊卡哇"),
                Tuple3.of(2, "药水哥", "金色维也纳")
        );

        ConnectedStreams<Tuple2<Integer, String>, Tuple3<Integer, String, String>> connect = data1.connect(data2);

        // keyBy 分区
        ConnectedStreams<Tuple2<Integer, String>, Tuple3<Integer, String, String>> connectKeyBy = connect.keyBy(o1 -> o1.f0, o2 -> o2.f0);
        SingleOutputStreamOperator<Object> process = connectKeyBy.process(new CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, String>, Object>() {
            final Map<String, Tuple2<Integer, String>> streamElement1 = new HashMap<>();
            final Map<String, Tuple3<Integer, String, String>> streamElement2 = new HashMap<>();

            @Override
            public void processElement1(Tuple2<Integer, String> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, String>, Object>.Context ctx, Collector<Object> out) throws Exception {
                String key = value.f0 + value.f1;
                if (!streamElement1.containsKey(key)) {
                    streamElement1.put(key, value);
                }

                if (streamElement2.containsKey(key)) {
                    Tuple3<Integer, String, String> currentElement = streamElement2.get(key);
                    out.collect(String.format("\"%s\"来自%s", value.f1, currentElement.f2));
                }
            }

            @Override
            public void processElement2(Tuple3<Integer, String, String> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, String>, Object>.Context ctx, Collector<Object> out) throws Exception {
                String key = value.f0 + value.f1;
                if (!streamElement2.containsKey(key)) {
                    streamElement2.put(key, value);
                }

                if (streamElement1.containsKey(key)) {
                    out.collect(String.format("\"%s\"来自%s", value.f1, value.f2));
                }
            }
        });
        process.print();
        env.execute();

        // 2> "乌萨奇"来自吉伊卡哇
        // 2> "孙笑川"来自6324
        // 2> "小八"来自吉伊卡哇
        // 2> "药水哥"来自金色维也纳
    }
}
