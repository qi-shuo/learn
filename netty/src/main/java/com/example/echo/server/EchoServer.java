package com.example.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-23 17:28
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
//            return;
//        }
        //int port = Integer.parseInt(args[0]);
        new EchoServer(8888).start();
    }

    public void start() throws Exception {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        //创建EventLoopGroup对象
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建SeverBootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    //指定使用Nio传输通道
                    .channel(NioServerSocketChannel.class)
                    //使用指定的端口作为socket地址
                    .localAddress(new InetSocketAddress(port))
                    //添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //EchoServerHandler使用了Sharable注解,所以我们总是使用同样的实例
                            ch.pipeline().addLast(echoServerHandler);
                        }
                    });
            //异步的绑定服务器调用方法sync阻塞等待直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            //获得channel的closeFuture,并且阻塞线程直到channel关闭完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅的关闭.释放所有的资源,阻塞线程直到完成
            group.shutdownGracefully().sync();
        }

    }
}
