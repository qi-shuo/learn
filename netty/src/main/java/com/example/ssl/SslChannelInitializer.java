package com.example.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * 添加SSL/TLS的支持
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-31 14:10
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {
    /**
     * 要使用的SslContext
     */
    private final SslContext sslContext;
    /**
     * 如果设置为true,第一个写入消息将不会被加密,客户端应该设置为true
     */
    private final boolean startTls;

    public SslChannelInitializer(SslContext sslContext, boolean startTls) {
        this.sslContext = sslContext;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        //对于每一个SslHandler实例,都是用Channel的ByteBufAllocator从SslContext获取一个新的SSLEngine
        SSLEngine engine = sslContext.newEngine(ch.alloc());
        //将SslHandler作为第一个ChannelHandler添加到ChannelPipeline中
        ch.pipeline().addFirst("ssl", new SslHandler(engine, startTls));

    }
}
