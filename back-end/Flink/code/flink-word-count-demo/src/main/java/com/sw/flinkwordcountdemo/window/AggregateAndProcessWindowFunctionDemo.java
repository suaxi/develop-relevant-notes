package com.sw.flinkwordcountdemo.window;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 增量集合函数、全窗口函数结合使用
 * 1. 增量聚合函数处理窗口数据（来一条处理一条）
 * 2. 全窗口函数触发计算时（增量聚合的结果传递给全窗口函数），处理逻辑输出结果
 *
 * @author：suaxi
 * @date：2026/11/21 15:39
 */
public class AggregateAndProcessWindowFunctionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001", 100),
                new User("1002", "刘波", "001", 50),
                new User("2001", "药水哥", "003", 30),
                new User("3001", "乌萨奇", "004", 200),
                new User("3002", "小八", "004", 20)
        );
        data.assignTimestampsAndWatermarks(WatermarkStrategy.<User>forMonotonousTimestamps().withTimestampAssigner((user, timestamp) -> System.currentTimeMillis()))
                .keyBy(User::getClassNo)
                .window(TumblingEventTimeWindows.of(Time.seconds(2)))
                .aggregate(new MyAggregateFunction(), new MyProcessFunction())
                .print();
        env.execute();

        // 创建累加器
        // 调用add()方法
        // 调用add()方法
        // 创建累加器
        // 调用add()方法
        // 创建累加器
        // 调用add()方法
        // 调用add()方法
        // 调用getResult()方法
        // 共有零花钱150元
        // 001窗口[2026-11-21 15:50:04.000 - 2026-11-21 15:50:06.000]包含1条数据
        // 调用getResult()方法
        // 共有零花钱220元
        // 004窗口[2026-11-21 15:50:04.000 - 2026-11-21 15:50:06.000]包含1条数据
        // 调用getResult()方法
        // 共有零花钱30元
        // 003窗口[2026-11-21 15:50:04.000 - 2026-11-21 15:50:06.000]包含1条数据
    }

    public static class MyAggregateFunction implements AggregateFunction<User, Integer, String> {

        @Override
        public Integer createAccumulator() {
            System.out.println("创建累加器");
            return 0;
        }

        @Override
        public Integer add(User value, Integer accumulator) {
            System.out.println("调用add()方法");
            return accumulator + value.getMoney();
        }

        @Override
        public String getResult(Integer accumulator) {
            System.out.println("调用getResult()方法");
            System.out.printf("共有零花钱%d元%n", accumulator);
            return accumulator.toString();
        }

        @Override
        public Integer merge(Integer a, Integer b) {
            return null;
        }
    }

    public static class MyProcessFunction extends ProcessWindowFunction<String, String, String, TimeWindow> {

        @Override
        public void process(String s, ProcessWindowFunction<String, String, String, TimeWindow>.Context context, Iterable<String> elements, Collector<String> out) throws Exception {
            long count = elements.spliterator().estimateSize();
            String start = DateFormatUtils.format(context.window().getStart(), "yyyy-MM-dd HH:mm:ss.SSS");
            String end = DateFormatUtils.format(context.window().getEnd(), "yyyy-MM-dd HH:mm:ss.SSS");
            out.collect(String.format("%s窗口[%s - %s]包含%d条数据", s, start, end, count));
        }
    }
}
