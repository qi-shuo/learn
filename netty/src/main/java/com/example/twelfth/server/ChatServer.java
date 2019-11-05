package com.example.twelfth.server;

import com.example.twelfth.server.pipeline.ChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-05 14:45
 */
public class ChatServer {
    /**
     * 创建一个DefaultChannelGroup将保存所有已经连接的WebSocket Channel
     */
    private ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private Channel channel;

    protected ChannelFuture start(InetSocketAddress address) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(createInitializer(channelGroup));
        ChannelFuture channelFuture = bootstrap.bind(address);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
        return channelFuture;
    }

    /**
     * 创建ChatServerInitializer
     *
     * @param group
     * @return
     */
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        return new ChatServerInitializer(group);
    }

    /**
     * 处理服务器关闭并且释放所有资源
     */
    protected void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        eventLoopGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Please give port as argument");
//            System.exit(1);
//        }
        int port = 8080;
        final ChatServer endPoit = new ChatServer();
        ChannelFuture channelFuture = endPoit.start(new InetSocketAddress(port));
        //添加一个关闭挂钩
        Runtime.getRuntime().addShutdownHook(new Thread(endPoit::destroy));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
