package com.example.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-23 16:55
 * @ChannelHandler.Sharable 标记一个channelHandler是共享的
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读
     * 对于每个传入的消息都要调用
     *
     * @param context
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        //将消息打印到控制台
        System.out.println("server received: " + in.toString(CharsetUtil.UTF_8));
        //将接收到的消息写给发送者,而不冲刷出站信息
        context.write(in);
    }

    /**
     * 阅读完成
     * 通知ChannelInboundHandler最后一次对channel-Read()的调用是当前批量读取中的最后一条消息
     *
     * @param context
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        //将未决消息冲刷到远程节点,并关闭通道
        context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 异常处理
     * @param context
     * @param throwable
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) {
        throwable.printStackTrace();
        //关闭通道
        context.close();
    }
}
