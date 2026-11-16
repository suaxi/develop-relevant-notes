package com.sw.flinkwordcountdemo.sink;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * @author：suaxi
 * @date：2026/11/16 23:06
 */
public class SinkMySQLDemo {
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

        SinkFunction<User> jdbcSink = JdbcSink.<User>sink(
                // sql
                "insert into t_user (id, name, class_no) values (?, ?, ?)",
                (statement, user) -> {
                    statement.setString(1, user.getId());
                    statement.setString(2, user.getUsername());
                    statement.setString(3, user.getClassNo());
                },
                // 执行参数
                JdbcExecutionOptions.builder()
                        .withBatchSize(500)
                        .withBatchIntervalMs(200)
                        .withMaxRetries(3)
                        .build(),
                new JdbcConnectionOptions
                        .JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:mysql://192.168.123.88:3306/flink-demo?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8")
                        .withDriverName("com.mysql.cj.jdbc.Driver")
                        .withUsername("root")
                        .withPassword("123456")
                        .build()
        );

        data.addSink(jdbcSink);
        env.execute();
    }
}
