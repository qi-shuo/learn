package com.example.twelfth.server.frame;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import com.example.twelfth.server.handler.HttpRequestHandler;

/**
 * 第十二章 聊天室的实现
 * 处理文本帧
 * WebSocket是以帧的方式传输数据,每一帧代表消息的一部分,一个完整的消息可能包含许多帧
 *
 *
 * 新的WebSocket握手成功后,
 * 他将消息写入到ChannelGroup中所有的Channel来通知所有已连接的客户端,
 * 然后将新的Channel加入到该ChannelGroup
 *
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-04 18:17
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    /**
     * 扩展SimpleChannelInboundHandler,并处理TextWebSocketFrame
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //如果接收到TextWebSocketFrame,增加消息的引用计数,并将它写到ChannelGroup中所用已连接的客户端
        group.writeAndFlush(msg.retain());

    }

    /**
     * 处理自定义事件
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果握手成功则从该ChannelPipeline中移除HttpRequestHandler因为将不会接受任何HTTP消息了
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
           ctx.pipeline().remove(HttpRequestHandler.class);
            //通知所有链接的webSocket客户端,新的客户端已经链接上
            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));
            //将新的WebSocket Channel添加到ChannelGroup中,以便他可以接收到所有的消息
            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
