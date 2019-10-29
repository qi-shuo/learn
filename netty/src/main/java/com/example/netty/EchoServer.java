//package com.example.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
//import java.net.InetSocketAddress;
//
///**
// * @author QiShuo
// * @version 1.0
// * @create 2019/3/8 4:08 PM
// */
//public class EchoServer {
//    private final int port;
//
//    public EchoServer(int port) {
//        this.port = port;
//    }
//
//    public static void main(String[] args) throws Exception {
//        if (args.length > 1) {
//            System.err.println("Usage" + EchoServer.class.getSimpleName() + "<port>");
//            //设置端口值
//            int port = Integer.parseInt(args[0]);
//            //调用服务的start方法
//            new EchoServer(port).start();
//        }
//    }
//
//    public void start() throws Exception {
//        final EchoServerHandler echoServerHandler = new EchoServerHandler();
//        //创建eventLoop对象
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        try {
//            //创建serverBootStrap
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(eventLoopGroup)
//                    //指定所使用的nio传输channel
//                    .channel(NioServerSocketChannel.class)
//                    //使用指定的端口设置套接字地址
//                    .localAddress(new InetSocketAddress(port))
//                    //添加一个echoServerHandle到子channel的channelPipeline
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            //echoServerHandler被标注为@Shareable,所以我们可以总是使用同样的实例
//                            ch.pipeline().addLast(echoServerHandler);
//                        }
//                    });
//            //异步的绑定服务器调用sync()阻塞等待直到绑定完成
//            ChannelFuture channelFuture = b.bind().sync();
//            //获取channel的channelFuture,并阻塞当前线程直到他完毕
//            channelFuture.channel().closeFuture().sync();
//        } finally {
//            //关闭eventLoopGroup释放资源
//            eventLoopGroup.shutdownGracefully().sync();
//        }
//
//    }
//}
