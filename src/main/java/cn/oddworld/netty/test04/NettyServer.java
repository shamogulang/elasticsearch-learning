package cn.oddworld.netty.test04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建bossgroup和workergroup
        // 创建了两个线程组，bossgroup和workergroup
        // bossgroup仅处理连接请求
        // workergroup真正和客户端业务处理
        // 两个都是无线循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

           // 创建服务器端启动的对象，可以配置参数
           ServerBootstrap bootstrap = new ServerBootstrap();
           // 使用链式编程的方式进行设置
           bootstrap.group(bossGroup,workerGroup)
                   .channel(NioServerSocketChannel.class) // 使用nioserversocketchannel作为服务器的通道实现
                   .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列等待连接的个数
                   .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                   .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道初始化对象

                       // 给pipline这是处理器。
                       @Override
                       protected void initChannel(SocketChannel socketChannel) throws Exception {
                           ChannelPipeline pipeline = socketChannel.pipeline();
                           pipeline.addLast(new NettyServerHandler());
                       }
                   });// 给workergroup的EventLoop对应的管道处理
           System.out.println("服务器准备好了");
           // 绑定一个端口并且同步生成了一个channelfuture对象。
           // 启动服务器（并绑定端口）
           ChannelFuture channelFuture = bootstrap.bind(6668).sync();
           // 对关闭通道进行监听
           channelFuture.channel().closeFuture().sync();
       }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
       }
    }
}
