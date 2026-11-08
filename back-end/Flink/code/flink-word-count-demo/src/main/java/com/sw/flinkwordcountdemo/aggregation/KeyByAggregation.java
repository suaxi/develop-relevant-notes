package com.sw.flinkwordcountdemo.aggregation;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/8 23:14
 */
public class KeyByAggregation {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001"),
                new User("1002", "刘波", "001"),
                new User("2001", "药水哥", "003"),
                new User("3001", "乌萨奇", "004"),
                new User("3002", "小八", "004")
        );

        // 方式一：使用 lambda
        // KeyedStream<User, String> keyedStream = data.keyBy(User::getId);

        // 方式二： 匿名内部类实现 KeySelector
        KeyedStream<User, String> keyedStream = data.keyBy(new KeySelector<User, String>() {
            @Override
            public String getKey(User value) throws Exception {
                return value.id;
            }
        });
    }
}
