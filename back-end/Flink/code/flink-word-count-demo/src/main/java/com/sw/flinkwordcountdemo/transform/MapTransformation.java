package com.sw.flinkwordcountdemo.transform;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/8 22:33
 */
public class MapTransformation {
    public static void main(String[] args) throws Exception {
        // 以提取 User 中的 username 为例
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001"),
                new User("1002", "刘波", "001"),
                new User("2001", "药水哥", "003"),
                new User("3001", "乌萨奇", "004"),
                new User("3002", "小八", "004")
        );

        // 方式一：lambda
        // data.map(User::getUsername).print();

        // 方式二：匿名内部类（实现MapFunction）
        // data.map(new MapFunction<User, String>() {
        //     @Override
        //     public String map(User value) throws Exception {
        //         return value.getUsername();
        //     }
        // });

        // 方式三：传入 MapFunction 的实现类
        data.map(new UserMapImpl()).print();

        // 7> 孙笑川
        // 10> 乌萨奇
        // 11> 小八
        // 8> 刘波
        // 9> 药水哥
        env.execute();
    }

    public static class UserMapImpl implements MapFunction<User, String> {

        @Override
        public String map(User value) throws Exception {
            return value.getUsername();
        }
    }
}
