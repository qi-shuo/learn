package com.example.fourth.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * 使用netty阻塞网络处理
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-29 15:07
 */
public class NettyOioServer {
    private void server(int port) {
        final ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi \r\n", CharsetUtil.UTF_8));
        EventLoopGroup eventLoopGroup = new OioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup)
                    //允许阻塞模式OIO
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //指定channelInitializer,对于每个已接收的链接都调用他
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加一个ChannelHandlerAdapter用于拦截处理事件
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext context) throws Exception {
                                    //将消息写入客户端,并添加ChannelFutureListener以便消息写完就关闭连接
                                    context.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);

                                }
                            });
                        }
                    });
            //绑定服务器接收链接
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            //获得channel的closeFuture,并且阻塞线程直到channel关闭完成
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                //优雅的关闭.释放所有的资源,阻塞线程直到完成
                eventLoopGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NettyOioServer().server(8080);
    }
}
