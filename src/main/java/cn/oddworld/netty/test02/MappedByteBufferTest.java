package cn.oddworld.netty.test02;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

    // 可以直接将文件在内存中修改，不需要拷贝进堆内
    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("D:\\hello.txt", "rw");
        FileChannel channel = rw.getChannel();
        // FileChannel.MapMode.READ_WRITE读写模式
        // 参数2：可以修改的起始位置
        // 参数3： 映射到内存的大小
        // 可以修改的范围的0到5
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte)'H');
        // 会indexoutofboundexception
        map.put(5,(byte)'H');
        rw.close();
    }
}
