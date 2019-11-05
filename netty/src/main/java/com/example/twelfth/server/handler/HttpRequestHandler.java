package com.example.twelfth.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 第十二章 聊天室的实现
 * <p>
 * 处理HTTP请求和响应(聊天服务器的一部分)
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-04 14:14
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUri;
    private static final File INDEX;

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Unable to locate index.html", e);
        }
    }

    /**
     * 实现如何转发任何目标URI为/ws的请求
     *
     * @param ctx
     * @param request
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        //如果请求了WebSocket协议升级,则增加引用计数(调用retain()方法),并将它传递给下一个ChannelInboundHandler
        if (wsUri.equalsIgnoreCase(request.uri())) {
            //如果该HTTP请求指向了地址为/ws的URI,那么HttpRequestHandler将调用FullHttpRequest对象上的retain()方法
            //并且调用fireChannelRead(msg)方法将他转发给下一个ChannelInboundHandler,之所以用retain()方法,是因为
            //调用channelRead()方法完成之后,它将调用FullHttpRequest对象上release()方法释放他的资源
            ctx.fireChannelRead(request.retain());
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {
                //客户端发送了HTTP 1.1的HTTP头信息Expect: 100-continue
                //那么HttpResponseHandler将会发送一个100 Continue响应
                send100Continue(ctx);
            }
            //“r” 以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
            //“rw” 打开以便读取和写入。如果该文件尚不存在，则尝试创建该文件。
            //“rws” 打开以便读取和写入，对于 “rw”，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备。
            //“rwd” 打开以便读取和写入，对于 “rw”，还要求对文件内容的每个更新都同步写入到底层存储设备。
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            //判断请求是否存活
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (keepAlive) {
                //如果请求到了keepAlive则添加所需要的HTTP的头信息
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            //设置头信息之后将HttpResponse写到客户端,这不是一个FullHttpResponse因为它只是响应的一部分
            ctx.write(response);
            if (ctx.pipeline().get(SslHandler.class) == null) {
                //如果不需要加密和压缩,直接将index.html写到客户端
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                //如果SslHandler存在使用ChunkedNioFile
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            //写一下LastHttpContent并冲刷至客户端
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!keepAlive) {
                //如果没有请求到keepAlive则写操作完成后关掉
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
