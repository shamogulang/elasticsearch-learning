package cn.oddworld.netty.test04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 1、我们自定义个handler，需要继承netty规定好的某个handler适配器
 * 2、这时我们自定义的handler才能称为handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送过来的消息
     * 1、ChannelHandlerContext 上下文对象，含有管道pipline，还有通道，还可以得到地址
     * 2、Object msg，客户端发送的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        // 将msg转成一个ByteBuf
        ByteBuf buf = (ByteBuf)msg;
        System.out.println("客户端发送的消息："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址："+ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕后，返回数据。
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client, i receve msg", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
