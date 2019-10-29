package com.example.fourth.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 第四章
 * 未使用netty的异步网络编程
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-24 16:43
 */
public class PlainNioServer {
    public void nioServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞通道
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress("127.0.0.1", port));
        //获得一个选择器
        Selector selector = Selector.open();
        //注册通道的选择器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer byteBuffer = ByteBuffer.wrap("Hi\r\n".getBytes());
        while (true) {
            //等待需要处理的新事件,阻塞将一直持续到下一个事件传入
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            //获取所用接收时间的selectionKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                //检查时间是否是一个新的可接受链接的事件
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = channel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, byteBuffer.duplicate());
                    System.out.println("链接来自于: " + client);
                    //检查socket是否是可写的状态
                    if (selectionKey.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        while (buffer.hasRemaining()) {
                            //将数据写到已连接的客户端中
                            if (socketChannel.write(buffer) == 0) {
                                break;
                            }
                        }
                        //关闭连接
                        socketChannel.close();

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainNioServer().nioServer(8080);
    }

}
