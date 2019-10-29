package com.example.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio阻塞模式
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-28 18:04
 */
public class BlockServer {
    public void server(int port) throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(() -> {

            try {
                //绑定端口
                ServerSocketChannel server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress(port));
                while (true) {
                    //获取客户端连接
                    SocketChannel client = server.accept();
                    //使用NIO,有了channel就必须有Buffer,Buffer与数据打交道
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (client.read(byteBuffer) != -1) {
                        //切换成读模式
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.limit()];
                        byteBuffer.get(bytes);
                        System.out.println("收到的客户端内容: " + new String(bytes));
                        //清理缓冲区
                        byteBuffer.clear();
                    }
                    byteBuffer.put("我收到了".getBytes());
                    byteBuffer.flip();
                    client.write(byteBuffer);
                    byteBuffer.clear();
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) throws IOException {
        new BlockServer().server(8080);
    }
}
