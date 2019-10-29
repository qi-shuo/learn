package com.example.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-23 18:15
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 服务器接收消息时候会调用(服务器有可能是分块接收)
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //记录接收消息的存储
        System.out.println("client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    /**
     * 将在链接建立是调用
     * @param context
     */
    @Override
    public void channelActive(ChannelHandlerContext context) {
        //当被通知的channel活跃时候发送一条消息
        context.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", Charset.defaultCharset()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        throwable.printStackTrace();
        context.close();
    }
}
