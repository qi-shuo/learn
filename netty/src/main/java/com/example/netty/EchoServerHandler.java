//package com.example.netty;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.*;
//import io.netty.util.CharsetUtil;
//
///**
// * @author QiShuo
// * @version 1.0
// * @create 2019/3/8 3:31 PM
// */
//
///**
// * 标记一个ChannelHandler能被多个Channel安全的共享
// */
//@ChannelHandler.Sharable
//public class EchoServerHandler extends ChannelHandlerAdapter {
////    @Override
////    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
////
////    }
//
//    /**
//     * 因为要处理所有接收到的数据重写channelRead
//     *
//     * @param ctx
//     * @param msg
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
//        //将接收到的消息共享到控制台
//        System.out.println("server received :" + in.toString(CharsetUtil.UTF_8));
//        //将接收到的消息写给发送者,而不冲刷出站信息
//        ctx.write(in);
//        // super.channelRead(ctx, msg);
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        //将未决消息冲刷到远程节点,并且关闭该channel
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
//        //super.channelReadComplete(ctx);
//    }
//
//    /**
//     * 允许对Throwable任何子类类型做出反应,在这里记录了异常并关闭了channel
//     *
//     * @param ctx
//     * @param cause
//     * @throws Exception
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        //super.exceptionCaught(ctx, cause);
//        //打印异常栈追踪
//        cause.printStackTrace();
//        //关闭channel
//        ctx.close();
//    }
//}
