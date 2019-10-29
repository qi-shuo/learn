package com.example.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-23 18:29
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        //使用Nio
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            //创建Bootstrap实例
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    //使用Nio的channel
                    .channel(NioSocketChannel.class)
                    //设置socket的地址
                    .remoteAddress(new InetSocketAddress(host, port))
                    //添加一个channelHandler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //链接远程节点,同步阻塞直到链接成功
            ChannelFuture future = bootstrap.connect().sync();
            //阻塞直到channel关闭
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            System.err.println("Usage: " + EchoClient.class.getSimpleName() + "<host> <port>");
//            return;
//        }
        //String host = args[0];
        //int port = Integer.parseInt(args[1]);
        new EchoClient("127.0.0.1", 8888).start();
    }
}
