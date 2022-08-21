package cn.oddworld.netty.test02;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel02 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\hello.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);

        while (true){
            byteBuffer.clear();
            int i = channel.read(byteBuffer);
            if(i == -1){
                break;
            }
            // 转成array然后输出
            System.out.println(new String(byteBuffer.array()));
        }
        fileInputStream.close();
    }
}
