package com.sw.flinkwordcountdemo.partitioning;

import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/9 23:08
 */
public class ShufflePartition {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> data = env.fromElements(1, 2, 3, 4, 5);

        // 随机分区：将流中的数据随机打乱，再传递到下游任务分区
        // data.shuffle().print();

        // 2> 1
        // 1> 3
        // 2> 2
        // 1> 5
        // 2> 4

        // 轮询：按顺序依次分发数据
        // data.rebalance().print();

        // 2> 1
        // 1> 2
        // 2> 3
        // 1> 4
        // 2> 5

        // 重缩放：与轮询类似，但 rescale 方法只给与自己对应的下游算子依次分发数据
        // data.rescale().print();

        // 1> 1
        // 2> 2
        // 1> 3
        // 2> 4
        // 1> 5

        // 广播：将流中的数据复制并发送给下游算子的所有并行任务中（每个分区都会收到相同的数据）
        // data.broadcast().print();

        // 1> 1
        // 2> 1
        // 1> 2
        // 2> 2
        // 1> 3
        // 2> 3
        // 1> 4
        // 2> 4
        // 1> 5
        // 2> 5

        // 全局分区：将流中的所有数据都发送到下游算子的第一个并行子任务中
        // data.global().print();

        // 1> 1
        // 1> 2
        // 1> 3
        // 1> 4
        // 1> 5

        // 自定义分区
        data.partitionCustom(new MyPartitioner(), key -> key).print();

        // 2> 1
        // 1> 2
        // 2> 3
        // 1> 4
        // 2> 5

        env.execute();
    }

    public static class MyPartitioner implements Partitioner<Integer> {

        @Override
        public int partition(Integer key, int numPartitions) {
            return key % numPartitions;
        }
    }
}
