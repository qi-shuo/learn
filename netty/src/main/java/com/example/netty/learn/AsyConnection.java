package com.example.netty.learn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.local.LocalServerChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 异步的建立连接
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-23 15:44
 */
public class AsyConnection {

    public void asyConnection() {
        Channel channel = new LocalServerChannel();
        //异步的链接大鹏远程节点
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("127.0.0.1", 80));
        //注册一个channelFutureListener以便操作完成后获取通知
        channelFuture.addListener((ChannelFutureListener) future -> {
            //判断是否操作成功
            if (future.isSuccess()) {
                //成功,创建一个ByteBuf以持有数据
                ByteBuf byteBuf = Unpooled.copiedBuffer("hello", Charset.defaultCharset());
                //将数据异步的返回远程节点返还一个ChannelFuture
                ChannelFuture write = future.channel().writeAndFlush(byteBuf);
            } else {
                //如果发生错误则描述错误原因
                Throwable cause;
                cause = future.cause();
                cause.getStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new AsyConnection().asyConnection();
    }
}
