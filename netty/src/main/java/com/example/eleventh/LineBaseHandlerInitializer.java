package com.example.eleventh;

import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.nio.ByteBuffer;

/**
 * 第十一章
 * 处理由行尾符分隔的帧
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-04 13:43
 */
public class LineBaseHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //该LineBaseFrameDecoder将提取的帧转给下一个ChannelInboundHandler
        pipeline.addLast(new LineBasedFrameDecoder(64 * 1024))
                //添加FrameHandler以接收帧
                .addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuffer> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {
            //Do something with the data extracted from the frame(处理从帧中提取的数据)
        }
    }
}
