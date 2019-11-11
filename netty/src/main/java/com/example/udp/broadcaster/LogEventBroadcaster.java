package com.example.udp.broadcaster;

import com.example.udp.coder.LogEventEncoder;
import com.example.udp.pojo.LogEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;


/**
 * LogEvent广播器
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-07 17:23
 */
public class LogEventBroadcaster {
    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private final File file;

    public LogEventBroadcaster(InetSocketAddress address, File file) {
        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        //引导该NioDatagramChannel(无连接的)
        bootstrap.group(group).channel(NioDatagramChannel.class)
                //设置SO_BROADCAST套接字选项
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void run() throws Exception {
        //绑定Channel
        Channel ch = bootstrap.bind(0).sync().channel();
        long pointer = 0;
        //启动主处理循环
        for (; ; ) {
            long len = file.length();
            if (len < pointer) {
                //文件已休息
                pointer = len;
            } else if (len > pointer) {
                //内容已添加
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                //保存当前文件指针,以确保没有任何的旧日志被发送
                raf.seek(pointer);
                String line;
                while ((line = raf.readLine()) != null) {
                    //对于每个日志条目写入一个LogEvent到Channel
                    ch.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), line));
                }
                //存储其在文件中的当前位置
                pointer = raf.getFilePointer();
                raf.close();
            }
            try {
                //休眠一秒如果被打断,贼退出循环,否则重新处理他
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("127.0.0.1", 8080), new File("test"));
        try {
            broadcaster.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            broadcaster.stop();
        }
    }
}

