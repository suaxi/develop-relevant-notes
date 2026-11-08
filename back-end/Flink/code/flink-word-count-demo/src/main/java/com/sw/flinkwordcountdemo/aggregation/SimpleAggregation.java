package com.sw.flinkwordcountdemo.aggregation;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/8 23:24
 */
public class SimpleAggregation {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001"),
                new User("1002", "刘波", "001"),
                new User("2001", "药水哥", "003"),
                new User("3001", "乌萨奇", "004"),
                new User("3002", "小八", "004")
        );

        /**
         * 参数说明：
         * positionToMax 元素类型为 Tuple 时可以使用位置指定聚合字段
         * field 当数据流元素类型为 POJO 时，只能使用 file 指定聚合字段
         */
        SingleOutputStreamOperator<User> streamOperator = data.keyBy(User::getClassNo).max("id");
        streamOperator.print();
        env.execute();

        // User{id='1001', username='孙笑川', classNo='001'}
        // User{id='1002', username='孙笑川', classNo='001'}
        // User{id='2001', username='药水哥', classNo='003'}
        // User{id='3001', username='乌萨奇', classNo='004'}
        // User{id='3002', username='乌萨奇', classNo='004'}
    }
}
