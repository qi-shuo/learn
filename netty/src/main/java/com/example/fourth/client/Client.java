package com.example.fourth.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-24 17:41
 */
public class Client {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 8080);
            //远程链接socket地址
            SocketAddress remoteSocketAddress = client.getRemoteSocketAddress();
            System.out.println("远程主机:" + remoteSocketAddress.toString());
            OutputStream outputStream = client.getOutputStream();
            InputStream inputStream = client.getInputStream();
            outputStream.write("java".getBytes());
            DataInputStream dataInputStream = new DataInputStream(inputStream);
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
