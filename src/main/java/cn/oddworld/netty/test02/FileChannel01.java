package cn.oddworld.netty.test02;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel01 {

    public static void main(String[] args) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream("D:\\hello.txt");

        final FileChannel channel = fileOutputStream.getChannel();
        final ByteBuffer allocate = ByteBuffer.allocate(1024);
        // 将string放入到bytebuffer中
        allocate.put("heloo".getBytes());

        allocate.flip();

        // 将缓存的数据写入到channel中
        final int write = channel.write(allocate);
        fileOutputStream.close();
    }
}