package com.example.learn.netty;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2018/11/15 2:02 PM
 */
public class NettyClient {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 8081);
            SocketAddress remoteSocketAddress = client.getRemoteSocketAddress();
            System.out.println("远程主机:" + remoteSocketAddress.toString());
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("Hello from" + client.getLocalSocketAddress());
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            System.out.println("服务器的响应:" + dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
