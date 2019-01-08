package com.learn.reptile.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

import java.io.*;
import java.net.URL;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019/1/3 6:19 PM
 */
public class HtmlUnitUtil {
    public static void makeHttpRequest(String url,String name) {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setDownloadImages(false);
        webClient.getOptions().setUseInsecureSSL(true);

        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setGeolocationEnabled(false);
        //当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        //是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setCssEnabled(false);
        //很重要，启用JS
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //很重要，设置支持AJAX
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        // HtmlPage page;
        try (InputStream contentAsStream = webClient.loadWebResponse(new WebRequest(new URL(url))).getContentAsStream();
             BufferedInputStream in = new BufferedInputStream(contentAsStream);
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/qishuo/code/music/" + name + ".mp3"))) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer, 0, 1024)) > -1) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            webClient.close();
        }

    }

    public static void main(String[] args) {//https://music.163.com/playlist?id=2203888771
    }
}
