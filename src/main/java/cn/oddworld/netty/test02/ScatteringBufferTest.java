package cn.oddworld.netty.test02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringBufferTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        open.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel accept = open.accept();
        int messageLent = 8;
        // 循环的读取
        while (true){
            int byteRead = 0;
            while (byteRead < messageLent){
                long read = accept.read(byteBuffers);
                byteRead += read;
                System.out.println("byteRead="+byteRead);
                Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> {
                    System.out.println("limit="+byteBuffer.limit());
                    System.out.println("position="+byteBuffer.position());
                });
            }
            Arrays.asList(byteBuffers).forEach(item -> item.flip());

            Arrays.asList(byteBuffers).forEach(item -> {
                System.out.println(new java.lang.String(item.array()));
            });
        }
    }
}
