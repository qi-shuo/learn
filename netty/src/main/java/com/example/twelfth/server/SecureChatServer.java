package com.example.twelfth.server;

import com.example.twelfth.server.pipeline.SecureChatServerInitializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.net.InetSocketAddress;

/**
 * 向ChatServer添加加密
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-05 16:38
 */
public class SecureChatServer extends ChatServer {
    private final SslContext context;

    public SecureChatServer(SslContext context) {
        this.context = context;
    }

    @Override
    protected ChannelInitializer<Channel> createInitializer(ChannelGroup group) {
        return new SecureChatServerInitializer(group, context);
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        //自签名证书
        SelfSignedCertificate cert = new SelfSignedCertificate();
        SslContext context = SslContextBuilder.forServer(cert.certificate(), cert.privateKey()).build();
        final SecureChatServer endPoint = new SecureChatServer(context);
        ChannelFuture channelFuture = endPoint.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(endPoint::destroy));
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
