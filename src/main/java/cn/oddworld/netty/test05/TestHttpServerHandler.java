package cn.oddworld.netty.test05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * SimpleChannelInboundHandler 是ChannelInboundHandlerAdapter子类
 * HttpObject 客户端和服务器端相互通讯的数据被封装成httpobject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    // 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest){
            System.out.println("pipeline hashcode"+ctx.pipeline().hashCode()+" TestHttpServerHandler hashcode"+this.hashCode());
            System.out.println("msg 类型 = "+msg.getClass());
            System.out.println("客户地址："+ctx.channel().remoteAddress());

            HttpRequest request = (HttpRequest)msg;
            URI uri = new URI(request.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("图标请求，直接返回，不处理！");
                return;
            }

            // 回复信息给客户端
            ByteBuf content = Unpooled.copiedBuffer("hello,i am server", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1
                    , HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的数据返回
            ctx.writeAndFlush(response);
        }

    }
}
