package com.sw.flinkwordcountdemo.transform;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/8 22:43
 */
public class FilterTransformation {
    public static void main(String[] args) throws Exception {
        // 过滤 classNo 等于 004 的数据
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<User> data = env.fromElements(
                new User("1001", "孙笑川", "001"),
                new User("1002", "刘波", "001"),
                new User("2001", "药水哥", "003"),
                new User("3001", "乌萨奇", "004"),
                new User("3002", "小八", "004")
        );

        // 方式一：匿名内部类
        // data.filter(item -> "004".equals(item.getClassNo())).print();

        // 方式二：传入 FilterFunction 的实现类
        data.filter(new UserFilterImpl()).print();
        env.execute();

        // 2> User{id='3002', username='小八', classNo='004'}
        // 1> User{id='3001', username='乌萨奇', classNo='004'}
    }

    public static class UserFilterImpl implements FilterFunction<User> {

        @Override
        public boolean filter(User value) throws Exception {
            return "004".equals(value.getClassNo());
        }
    }
}
