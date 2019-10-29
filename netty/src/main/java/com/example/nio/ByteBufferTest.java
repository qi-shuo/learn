package com.example.nio;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.nio.ByteBuffer;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019-10-28 17:22
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        System.out.println("-------------------------");
        String str = "java";
        byteBuffer.put(str.getBytes());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        System.out.println("-------------------------");
        //切换成读模式
        byteBuffer.flip();
        //清空缓冲区,切换成写模式
        //byteBuffer.clear();
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        System.out.println("-----------------");
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());

    }
}
