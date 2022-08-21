package cn.oddworld.netty.test04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("client"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server, i am client...", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client ctx = " + ctx);
        // 将msg转成一个ByteBuf
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("服户端发送的消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("服户端的地址："+ctx.channel().remoteAddress());
    }
}
