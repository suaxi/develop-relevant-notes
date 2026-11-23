package com.sw.flinkwordcountdemo.window;

import com.sw.flinkwordcountdemo.pojo.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 处理窗口函数
 *
 * @author：suaxi
 * @date：2026/11/21 15:24
 */
public class ProcessWindowFunctionDemo {
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
                .window(TumblingProcessingTimeWindows.of(Time.milliseconds(50)))
                .process(new ProcessWindowFunction<User, String, String, TimeWindow>() {
                    @Override
                    public void process(String s, ProcessWindowFunction<User, String, String, TimeWindow>.Context context, Iterable<User> elements, Collector<String> out) throws Exception {
                        long count = elements.spliterator().estimateSize();
                        String start = DateFormatUtils.format(context.window().getStart(), "yyyy-MM-dd HH:mm:ss.SSS");
                        String end = DateFormatUtils.format(context.window().getEnd(), "yyyy-MM-dd HH:mm:ss.SSS");
                        out.collect(String.format("%s窗口[%s - %s]包含%d条数据", s, start, end, count));
                    }
                })
                .print();
        env.execute();

        // 001窗口[2026-11-21 15:35:39.700 - 2026-11-21 15:35:39.750]包含1条数据
        // 004窗口[2026-11-21 15:35:39.750 - 2026-11-21 15:35:39.800]包含2条数据
        // 003窗口[2026-11-21 15:35:39.750 - 2026-11-21 15:35:39.800]包含1条数据
        // 001窗口[2026-11-21 15:35:39.750 - 2026-11-21 15:35:39.800]包含1条数据
    }
}
