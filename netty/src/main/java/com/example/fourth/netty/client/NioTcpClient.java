package com.example.fourth.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导使用一个NIO TCP的传输客户端
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-30 17:45
 */
public class NioTcpClient {
    private void client() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                //.remoteAddress(new InetSocketAddress("", 8080))
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("received data");
                    }
                });
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                System.out.println("connection established");
            } else {
                System.err.println("Connection attempt failed");
                future1.cause().printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        new NioTcpClient().client();
    }
}
