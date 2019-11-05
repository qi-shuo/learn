package com.example.websocket.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 在服务端支持webSocket
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-31 18:29
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        //要想为WebSocket添加安全性,主需要将SslHandler作为第一个ChannelHandler添加到ChannelPipeline中
        ch.pipeline().addLast(new HttpServerCodec(),
                //为握手提供聚合的HttpRequest
                new HttpObjectAggregator(65536),
                //如果被请求的端点是"/websocket"则处理该升级握手
                new WebSocketServerProtocolHandler("/websocket"),
                //处理TextWebSocketFrame
                new TextFrameHandler(),
                //处理BinaryWebSocketFrame
                new BinaryFrameHandler(),
                //处理ContinuationWebSocketFrame
                new ContinuationFrameHandler());
    }
    //以下是WebSocketFrame的类型

    /**
     * TextWebSocketFrame数据帧:文本数据
     */
    public static final class TextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            //处理text frame
        }
    }

    /**
     * BinaryWebSocketFrame数据帧:二进制数据
     */
    public static final class BinaryFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
            //处理binary frame
        }
    }

    /**
     * ContinuationWebSocketFrame数据帧:属于上一个BinaryWebSocketFrame或者TextWebSocketFrame的文本的或者二进制数据
     */
    public static final class ContinuationFrameHandler extends SimpleChannelInboundHandler<ContinuationWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ContinuationWebSocketFrame msg) throws Exception {
            //处理continuation frame
        }
    }

}
