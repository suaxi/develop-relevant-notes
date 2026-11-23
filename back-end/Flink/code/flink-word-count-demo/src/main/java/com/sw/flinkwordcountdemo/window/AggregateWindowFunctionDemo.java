package com.sw.flinkwordcountdemo.window;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author：suaxi
 * @date：2026/11/21 15:11
 */
public class AggregateWindowFunctionDemo {
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
                .aggregate(new AggregateFunction<User, Integer, String>() {
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
                })
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
        // 150
        // 调用getResult()方法
        // 共有零花钱220元
        // 220
        // 调用getResult()方法
        // 共有零花钱30元
        // 30
    }
}
