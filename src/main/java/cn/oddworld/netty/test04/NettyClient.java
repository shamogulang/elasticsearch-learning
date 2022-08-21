package cn.oddworld.netty.test04;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                        @Override
                        protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            // channelFuture后续要分析，因为涉及到异步模型
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 6668).sync();

            sync.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
