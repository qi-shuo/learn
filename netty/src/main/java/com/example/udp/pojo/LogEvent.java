package com.example.udp.pojo;

import java.net.InetSocketAddress;

/**
 *
 在消息处理应用程序中，数据通常由 POJO 表示，除了实际上的消息内容，其还可以包含配 置或处理信息。在这个应用程序中，我们将会把消息作为事件处理，并且由于该数据来自于日志 文件，所以我们将它称为 LogEvent。
 * LogEvent消息
 * @author QiShuo
 * @version 1.0
 * @create 2019-11-07 15:20
 */
public class LogEvent {
    /**
     * 分离器
     */
    public static final byte SEPARATOR = ':';
    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    /**
     * 收到
     */
    private final long received;

    /**
     * 用于传出消息的构造函数
     *
     * @param logfile
     * @param msg
     */
    public LogEvent(String logfile, String msg) {
        this(null, -1, logfile, msg);
    }

    /**
     * 用于传入消息的构造函数
     *
     * @param source
     * @param received
     * @param logfile
     * @param msg
     */
    public LogEvent(InetSocketAddress source, long received, String logfile, String msg) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    /**
     * 返回发送LogEvent的源InetSocketAddress
     * @return
     */
    public InetSocketAddress getSource() {
        return source;
    }

    /**
     * 返回发送的LogEvent的日志文件的名称
     * @return
     */
    public String getLogfile() {
        return logfile;
    }

    /**
     * 返回消息内容
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 获取接收时间戳
     *
     * @return
     */
    public long getReceivedTimestamp() {
        return received;
    }
}
