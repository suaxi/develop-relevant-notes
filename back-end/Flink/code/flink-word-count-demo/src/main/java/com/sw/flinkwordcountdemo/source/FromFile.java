package com.sw.flinkwordcountdemo.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author：suaxi
 * @date：2026/11/7 21:56
 */
public class FromFile {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FileSource<String> data = FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("input/wordCount.txt")).build();
        env.fromSource(data, WatermarkStrategy.noWatermarks(), "file").print();
        env.execute();

        // 5> sun xiao chuan
        // 5> yao shui ge
        // 5> liu bo
        // 5> Giao ge
    }
}
