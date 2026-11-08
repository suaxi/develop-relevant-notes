package com.sw.flinkwordcountdemo.aggregation;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/8 23:39
 */
public class ReduceAggregation {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001", 10),
                new User("1002", "刘波", "001", 15),
                new User("2001", "药水哥", "003", 20),
                new User("3001", "乌萨奇", "004", 5),
                new User("3002", "小八", "004", 3)
        );

        data
                .keyBy(User::getClassNo)
                .reduce(((value1, value2) -> {
                    int money = value1.getMoney() + value1.getMoney();
                    System.out.println(value1.getClassNo() + "班的学生共有" + money + "块零花钱");
                    return value1;
                })).print();
        env.execute();

        // User{id='1001', username='孙笑川', classNo='001', money=10}
        // 001班的学生共有20块零花钱
        // User{id='1001', username='孙笑川', classNo='001', money=10}
        // User{id='2001', username='药水哥', classNo='003', money=20}
        // User{id='3001', username='乌萨奇', classNo='004', money=5}
        // 004班的学生共有10块零花钱
        // User{id='3001', username='乌萨奇', classNo='004', money=5}
    }
}
