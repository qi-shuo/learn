package com.example.fourth.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第四章
 * 未使用netty的阻塞网络编程
 *
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-24 16:43
 */
public class PlainOioServer {
    public void oioServer(int port) {
        try {
            //将服务器绑定到指定端口
            final ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                //接收链接
                Socket clientSocket = serverSocket.accept();
                System.out.println("接收链接来自: " + clientSocket);
                //创建新线程处理链接
                ExecutorService es = Executors.newFixedThreadPool(1);
                es.execute(() -> {
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                        dataOutputStream.writeUTF("Hi! \r\n" + clientSocket.getLocalSocketAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PlainOioServer().oioServer(8080);
    }
}
