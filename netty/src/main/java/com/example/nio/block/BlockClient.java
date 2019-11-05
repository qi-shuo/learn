package com.example.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * nio阻塞模式客户端
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-28 18:05
 */
public class BlockClient {
    public void client(int port) throws IOException {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress(port));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("哈哈".getBytes());
        byteBuffer.flip();
        client.write(byteBuffer);
        byteBuffer.clear();
        //告诉服务器已经写完了
        client.shutdownOutput();
        if (client.read(byteBuffer) != -1) {
            //切换成读模式
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            System.out.println(new String(bytes));
            //切换成写模式,清理缓冲区
            byteBuffer.clear();
        }
    }

    public static void main(String[] args) throws IOException {
        new BlockClient().client(8080);
    }

}
