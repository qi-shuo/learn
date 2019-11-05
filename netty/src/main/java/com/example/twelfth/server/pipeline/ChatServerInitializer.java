package com.example.twelfth.server.pipeline;


import com.example.twelfth.server.frame.TextWebSocketFrameHandler;
import com.example.twelfth.server.handler.HttpRequestHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化ChannelPipeline
 * 聊天服务器初始值设定项
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-05 14:08
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;

    public ChatServerInitializer(ChannelGroup group) {
        this.group = group;
    }

    /**
     * initChannel()方法的调用,通过安装所有必须的ChannelHandler来设置该新注册的Channel的ChannelPipeline
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //将字节解码为HttRequest,HttpContent和LastHttpConTent.
        //并将HttpRequest,HttpContent和LastHttpContent编码为字节
        pipeline.addLast(new HttpServerCodec());
        //写入一个文件内容你那个
        pipeline.addLast(new ChunkedWriteHandler());
        //将一个HttpMessage和跟随他的多个HttpContent聚合为单个FullHttRequest
        //或者FullHttpResponse(取决于他是用来处理请求还是响应),安装这个只会,
        //ChannelPipeline中下一个ChannelHandler将只会收到完整的HTTP请求和响应
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //处理FullHttpRequest请求(那些不发送到/ws URI的请求)
        pipeline.addLast(new HttpRequestHandler("/ws"));
        //按照WebSocket规范要求处理WebSocket升级握手,PingWebSocketFrame,PongWebSocketFrame和CloseWebSocketFrame
        //Netty的WebSocketServerProtocolHandler处理了所有委托的WebSocket帧类型以及升级握手,
        //如果握手成功那么所需的ChannelHandler将会添加到ChannelPipeline中而那些不再需要的ChannelHandler将会被移除
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //处理TextWebSocketFrame和握手完成事件
        pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
