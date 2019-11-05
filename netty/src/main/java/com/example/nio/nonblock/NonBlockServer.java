package com.example.nio.nonblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * NIO非阻塞服务端
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-29 11:35
 */
public class NonBlockServer {
    /**
     * @param port 将Socket注册到Selector中,监听感兴趣的事件
     *             当感兴趣的事件准备就绪时,则会进去我们处理的方法进行处理
     *             每处理完一次就绪事件,删除该选择键,因为我们处理完了
     */
    public void nonBlockServer(int port) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            //配置为非阻塞
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            Selector selector = Selector.open();
            //将通道注册到选择器上,指定接收监听通道事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            //轮询获取选择器上的就绪事件只要select>0,说明已经就绪
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //获取已经就绪的事件,不同的事件做不同的事情
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    //接收事件就绪
                    if (next.isAcceptable()) {
                        //获取客户端连接
                        SocketChannel client = server.accept();
                        //设置为非阻塞
                        client.configureBlocking(false);
                        //将客户端注册到选择器上,拿到客户端连接为了读取通道的数据(监听读的就绪事件)
                        client.register(selector, SelectionKey.OP_READ);
                        //读事件就绪
                    } else if (next.isReadable()) {
                        //获取当前选择器读就绪状态的通道
                        SocketChannel client = (SocketChannel) next.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        if (client.read(byteBuffer) != -1) {
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.limit()];
                            byteBuffer.get(bytes);
                            System.out.println("我接受到客户端的内容是: " + new String(bytes));
                            byteBuffer.clear();
                        }
                        byteBuffer.put("我是服务端,我接受成功了".getBytes());
                        byteBuffer.flip();
                        client.write(byteBuffer);
                        byteBuffer.clear();
                        client.close();
                    }
                    //已经处理的选择键应该取消了
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NonBlockServer().nonBlockServer(8080);
    }

}
