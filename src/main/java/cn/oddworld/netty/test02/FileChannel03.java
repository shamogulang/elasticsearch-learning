package cn.oddworld.netty.test02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// 文件的拷贝
public class FileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\hello.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);

        FileOutputStream os = new  FileOutputStream("D:\\hello2.txt");
        FileChannel channel1 = os.getChannel();

        while (true){
            // 把标志位重置，清空数据
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            channel1.write(byteBuffer);
        }

        fileInputStream.close();
        os.close();
    }
}
