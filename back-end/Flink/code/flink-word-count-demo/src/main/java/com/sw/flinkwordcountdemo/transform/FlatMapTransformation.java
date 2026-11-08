package com.sw.flinkwordcountdemo.transform;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author：suaxi
 * @date：2026/11/8 22:49
 */
public class FlatMapTransformation {
    public static void main(String[] args) throws Exception {
        // classNo 等于 001 时输出 username，classNo 等于 004 时输出 id 和 username
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001"),
                new User("1002", "刘波", "001"),
                new User("2001", "药水哥", "003"),
                new User("3001", "乌萨奇", "004"),
                new User("3002", "小八", "004")
        );

        // 方式一：匿名内部类
        // data.flatMap((User value, Collector<String> out) -> {
        //     if ("001".equals(value.getClassNo())) {
        //         out.collect(value.getUsername());
        //     }
        //
        //     if ("004".equals(value.getClassNo())) {
        //         out.collect(value.getId());
        //         out.collect(value.getUsername());
        //     }
        // }).returns(Types.STRING).print();

        // 方式二：传入 FilterFunction 的实现类
        data.flatMap(new FlatMapImpl()).print();
        env.execute();

        // 孙笑川
        // 刘波
        // 3001
        // 乌萨奇
        // 3002
        // 小八
    }

    public static class FlatMapImpl implements FlatMapFunction<User, String> {

        @Override
        public void flatMap(User value, Collector<String> out) throws Exception {
            if ("001".equals(value.getClassNo())) {
                out.collect(value.getUsername());
            }

            if ("004".equals(value.getClassNo())) {
                out.collect(value.getId());
                out.collect(value.getUsername());
            }
        }
    }
}
