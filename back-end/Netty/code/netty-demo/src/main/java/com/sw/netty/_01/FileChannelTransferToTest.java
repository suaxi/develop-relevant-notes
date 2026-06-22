package com.sw.netty._01;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * @author suaxi
 * @date 2026/06/22 22:44
 */
public class FileChannelTransferToTest {
    public static void main(String[] args) {
        URL resource = ScatteringReadsTest.class.getClassLoader().getResource("ByteBufferTest.txt");
        if (resource == null) {
            throw new IllegalArgumentException("resource not found: ScatteringReadsTest.txt");
        }

        // try (FileChannel from = FileChannel.open(Paths.get(resource.toURI()));
        //      FileChannel to = new FileOutputStream("FileChannelTransferTo.txt").getChannel()) {
        //     // transferTo 一次最多传输 2G 的数据
        //     from.transferTo(0, from.size(), to);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        try (FileChannel from = FileChannel.open(Paths.get(resource.toURI()));
             FileChannel to = new FileOutputStream("FileChannelTransferTo.txt").getChannel()) {

            // 传输大于 2G 的文件
            long size = from.size();
            for (long left = size; left > 0; ) {
                left -= from.transferTo((size - left), left, to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
