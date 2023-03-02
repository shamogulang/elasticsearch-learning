package cn.oddworld.netty.test05;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();
        // 加入netty提供的httpServerCodec 编码和解码器
        // netty提供的基于http的编解码器
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        // 增加一个自定义的处理器

        pipeline.addLast(new TestHttpServerHandler());
    }
}
