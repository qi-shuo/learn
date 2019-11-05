package com.example.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 添加对http的支持
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-31 14:45
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {
    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            //如果是客户端的话添加HttpResponseDecoder来处理来自服务端的响应
            pipeline.addLast("decoder", new HttpResponseDecoder());
            //如果是客户端,添加HttpRequestEncoder想服务端大宋请求
            pipeline.addLast("encoder", new HttpRequestEncoder());
        } else {
            //如果是服务端,添加HttpRequestDecoder,来接受来自客户端的请求
            pipeline.addLast("decoder", new HttpRequestDecoder());
            //如果是服务端,添加HttpResponseEncoder,来向客户端发送响应
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}
