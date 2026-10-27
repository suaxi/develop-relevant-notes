package com.sw.flinkwordcountdemo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * DataStream API demo
 *
 * @author：suaxi
 * @date：2026/10/27 21:56
 */
public class WordCountStreamDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> ds = env.readTextFile("input/wordCount.txt");
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordTuple2FlatMap = ds.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (value, out) -> {
            String[] wordArr = value.split(" ");
            for (String word : wordArr) {
                Tuple2<String, Integer> wordTuple2 = Tuple2.of(word, 1);
                out.collect(wordTuple2);
            }
        }).returns(Types.TUPLE(Types.STRING, Types.INT));

        // 分组
        KeyedStream<Tuple2<String, Integer>, String> wordKeyBy = wordTuple2FlatMap.keyBy((KeySelector<Tuple2<String, Integer>, String>) value -> value.f0);

        // 聚合
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = wordKeyBy.sum(1);
        sum.print();
        env.execute();
    }
}
