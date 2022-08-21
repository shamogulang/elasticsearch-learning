package cn.oddworld.netty.test03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class NioClientTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("客户端不会阻塞，我可以做其他工作");
            }
        }
        // 如果连接成功，那么我门发送数据
        // wrap可以直接根据大小放入数据
        ByteBuffer wrap = ByteBuffer.wrap("hello,jeffchan".getBytes());
        socketChannel.write(wrap);
        TimeUnit.SECONDS.sleep(1000);
    }
}
