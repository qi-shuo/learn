package com.example.nio.nonblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO非阻塞客户端
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-29 11:35
 */
public class NonBlockClient {
    public void nonBlockClient(int port) {
        try {
            SocketChannel client = SocketChannel.open();
            client.connect(new InetSocketAddress(port));
            //设置非阻塞形式
            client.configureBlocking(false);
            //获取选择器
            Selector selector = Selector.open();
            //将通道注册到选择器中,获取服务端返回结果
            client.register(selector, SelectionKey.OP_READ);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("我是客户端发送的内容".getBytes());
            byteBuffer.flip();
            client.write(byteBuffer);
            byteBuffer.clear();
            //轮询的获取选择器上准备就绪的事件select>0,说明已经就绪
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //判断读事件是否就绪
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                        if (channel.read(responseBuffer) != -1) {
                            responseBuffer.flip();
                            byte[] bytes = new byte[responseBuffer.limit()];
                            responseBuffer.get(bytes);
                            System.out.println("客户端接收的内容是: " + new String(bytes));
                        }
                    }
                    //取消选择键
                    iterator.remove();
                }
            }
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NonBlockClient().nonBlockClient(8080);
    }
}
