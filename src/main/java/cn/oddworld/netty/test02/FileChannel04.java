package cn.oddworld.netty.test02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

// 文件拷贝
public class FileChannel04 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\hello.txt");
        FileChannel sourceChannel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\hellox.txt");
        FileChannel dstChannel = fileOutputStream.getChannel();

        dstChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
        dstChannel.close();
        sourceChannel.close();
    }
}
