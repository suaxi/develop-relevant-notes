package com.sw.flinkwordcountdemo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author：suaxi
 * @date：2026/10/26 22:39
 */
public class WordCountBatchDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建执行单元
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2. 读取数据
        DataSource<String> ds = env.readTextFile("input/wordCount.txt");

        // 3. 切分、转换
        FlatMapOperator<String, Tuple2<String, Integer>> wordTuple2FlatMap = ds.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (value, out) -> {
            String[] wordArr = value.split(" ");
            for (String word : wordArr) {
                Tuple2<String, Integer> wordTuple2 = Tuple2.of(word, 1);
                out.collect(wordTuple2);
            }
        }).returns(Types.TUPLE(Types.STRING, Types.INT));

        // 4. 分组
        UnsortedGrouping<Tuple2<String, Integer>> wordGroupBy = wordTuple2FlatMap.groupBy(0);

        // 5. 聚合
        AggregateOperator<Tuple2<String, Integer>> sum = wordGroupBy.sum(1);
        sum.print();
    }
}
