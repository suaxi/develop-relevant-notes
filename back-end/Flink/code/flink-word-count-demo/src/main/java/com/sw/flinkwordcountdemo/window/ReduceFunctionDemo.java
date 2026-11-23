package com.sw.flinkwordcountdemo.window;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author：suaxi
 * @date：2026/11/20 23:59
 */
public class ReduceFunctionDemo {
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

        data.keyBy(User::getClassNo)
                // 设置滚动事件事件窗口
                .window(TumblingProcessingTimeWindows.of(Time.seconds(2)))
                .reduce(new ReduceFunction<User>() {
                    @Override
                    public User reduce(User value1, User value2) throws Exception {
                        System.out.printf("共有零花钱%d元%n", value1.getMoney() + value1.getMoney());
                        return new User(null, null, value1.getClassNo(), value1.getMoney() + value2.getMoney());
                    }
                })
                .print();
        env.execute();

        // 共有零花钱200元
        // 共有零花钱400元
    }
}
