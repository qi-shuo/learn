package com.example.fourth.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.ScheduledFuture;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 使用netty非阻塞网络处理
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-29 15:08
 */
public class NettyNioServer {
    private void server(int port) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hi\r\n", CharsetUtil.UTF_8);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .localAddress(new InetSocketAddress(port))
                    .channel(NioServerSocketChannel.class)
                    //指定ChannelInitializer每个链接都调用他
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加channelHandlerAdapter用于接受处理时间内
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext context) {
                                    //将消息写到客户端,添加一个ChannelFutureListener,用于消息写完就关闭连接
                                    context.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            //绑定服务器接收链接
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放所有资源
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new NettyNioServer().server(8080);
//        Charset utf8 = Charset.forName("UTF-8");
//        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        //copy()方法调用数据不共享slice()方法调用之后产生的数据是共享的
//        ByteBuf copy = buf.copy(0, 15);
//        System.out.println(copy.toString(utf8));
//        buf.setByte(0, (byte)'J');
//        System.out.println(buf.getByte(0) == copy.getByte(0));
//        System.out.println(copy.toString(utf8));
//        System.out.println(buf.toString(utf8));
    }
}
