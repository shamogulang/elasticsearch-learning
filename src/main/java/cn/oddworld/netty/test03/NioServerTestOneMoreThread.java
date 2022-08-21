package cn.oddworld.netty.test03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioServerTestOneMoreThread {


    public static void main(String[] args) throws IOException {

        // 创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到一个selector实例
        Selector selector = Selector.open();

        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serversocketChannel注册到selector，关心的是连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待连接
        while (true){
            System.out.println("注册key入口的数量key="+ selector.keys().size());
            if(selector.select(1000) == 0){
                System.out.println("服务器没有连接事件发生");
                continue;
            }
            // 如果返回大于0，获取到相关的selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                // 根据key不同的事件进行相应处理
                Dispatch1.dispatch(key, serverSocketChannel, selector);
                iterator.remove();
            }
        }
    }


}

class Dispatch1{

    public static void dispatch(SelectionKey key, ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        if(key.isAcceptable()){
            AcceptorHandler1.handle(serverSocketChannel, selector);
        }else if(key.isReadable()){
            ReadHandler1.handler(key);
        }
    }
}

class AcceptorHandler1{

    public static void handle(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel accept = serverSocketChannel.accept();
        System.out.println("客户端进行连接"+accept);
        accept.configureBlocking(false);
        // 注册通道，同时关联一个buffer
        accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }
}

class ReadHandler1{

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void handler(SelectionKey key) throws IOException {
        executorService.submit(()->{
            // 通过key反向获取对应的channel
            SocketChannel channel = (SocketChannel)key.channel();
            ByteBuffer attachment = (ByteBuffer)key.attachment();
            try {
                channel.read(attachment);
            } catch (IOException e) {
                e.printStackTrace();
            }
            attachment.flip();
            System.out.println(new String(attachment.array()));
        });
    }
}