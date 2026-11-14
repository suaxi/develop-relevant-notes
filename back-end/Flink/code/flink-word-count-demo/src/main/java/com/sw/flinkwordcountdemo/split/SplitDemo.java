package com.sw.flinkwordcountdemo.split;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @author：suaxi
 * @date：2026/11/14 21:25
 */
public class SplitDemo {
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

        // 以侧输出流（侧流）为例
        OutputTag<User> split1 = new OutputTag<>("split-1", Types.POJO(User.class)) {
        };
        OutputTag<User> split2 = new OutputTag<>("split-2", Types.POJO(User.class)) {
        };
        OutputTag<User> split3 = new OutputTag<>("split-3", Types.POJO(User.class)) {
        };

        SingleOutputStreamOperator<User> process = data.process(new ProcessFunction<>() {
            @Override
            public void processElement(User value, ProcessFunction<User, User>.Context ctx, Collector<User> out) throws Exception {
                if ("001".equals(value.getClassNo())) {
                    ctx.output(split1, value);
                } else if ("003".equals(value.getClassNo())) {
                    ctx.output(split2, value);
                } else {
                    out.collect(value);
                }
            }
        });

        process.print("主流");
        SideOutputDataStream<User> sideOutput1 = process.getSideOutput(split1);
        SideOutputDataStream<User> sideOutput2 = process.getSideOutput(split2);
        SideOutputDataStream<User> sideOutput3 = process.getSideOutput(split3);
        sideOutput1.print("侧流1");
        sideOutput2.print("侧流2");
        sideOutput3.print("侧流3");
        env.execute();

        // 侧流1> User{id='1001', username='孙笑川', classNo='001', money=null}
        // 侧流1> User{id='1002', username='刘波', classNo='001', money=null}
        // 侧流2> User{id='2001', username='药水哥', classNo='003', money=null}
        // 主流> User{id='3001', username='乌萨奇', classNo='004', money=null}
        // 主流> User{id='3002', username='小八', classNo='004', money=null}
    }
}
