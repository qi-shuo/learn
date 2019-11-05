package com.example.websocket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 发送心跳,处理空闲连接
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-04 11:34
 */
public class IdleStateHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //IdleStateHandler将在被触发是发送一个IdleStateEvent事件
        channelPipeline.addLast(new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS))
                //将一个HeartbeatHandler添加到ChannelPipeline中
                .addLast(new HeartbeatHandler());

    }

    /**
     * 实现userEventTriggered
     */
    public static final class HeartbeatHandler extends ChannelInboundHandlerAdapter {
        private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HEARTBEAT", CharsetUtil.ISO_8859_1));

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                //发送信条消息,并且发送失败时候关闭连接
                ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            } else {
                //不是IdleStateEvent时间,所以将他传递给下一个ChannelHandler
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}
