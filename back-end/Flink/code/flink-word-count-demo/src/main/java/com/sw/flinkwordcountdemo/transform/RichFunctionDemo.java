package com.sw.flinkwordcountdemo.transform;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/9 22:59
 */
public class RichFunctionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env
                .setParallelism(1)
                .fromElements(1, 2, 3, 4, 5)
                .map(new RichMapFunction<Integer, String>() {
                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        System.out.println("作业[" + getRuntimeContext().getJobId() + "]初始化，调用open()方法");
                    }

                    @Override
                    public void close() throws Exception {
                        super.close();

                        System.out.println("作业[" + getRuntimeContext().getJobId() + "]执行完成，调用close()方法");
                    }

                    @Override
                    public String map(Integer value) throws Exception {
                        return "处理输入数据：" + value;
                    }
                })
                .print();
        env.execute();

        // 作业[8aeb80df701cada21206486ca97584c7]初始化，调用open()方法
        // 处理输入数据：1
        // 处理输入数据：2
        // 处理输入数据：3
        // 处理输入数据：4
        // 处理输入数据：5
        // 作业[8aeb80df701cada21206486ca97584c7]执行完成，调用close()方法
    }
}
