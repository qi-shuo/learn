package com.example.netty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2018/11/15 2:23 PM
 */
public class NettyServer {
    private ServerSocket serverSocket;

    public NettyServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            Socket server = null;
            System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
            try {
                server = serverSocket.accept();
                System.out.println("远程主机地址:" + server.getRemoteSocketAddress());
                DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
                System.out.println("输入的内容:" + dataInputStream.readUTF());
                DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeUTF("谢谢你链接我:" + server.getLocalSocketAddress());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }finally {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable=()->new NettyServer(8081).run();
        runnable.run();
    }
}
