package com.learn.reptile.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019/1/3 6:27 PM
 */
public class NetMusicGrab {
    private static String playListUrl = "/playlist?id=2203888771";
    private static String url = "https://music.163.com";
    private static String songUrl = "http://music.163.com/song/media/outer/url?id=";

    /**
     * 启动main方法下载网易云音乐歌曲到本地
     * @param args
     */
    public static void main(String[] args) {
        Connection connect = Jsoup.connect(url + playListUrl);
        List<Map<String, String>> songList = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements liList = document.getElementsByTag("li");
            for (Element li : liList) {
                String title = li.getElementsByTag("a").text();
                String attr = li.getElementsByTag("a").first().attr("href");
                if (attr.contains("/song?id=")) {
                    Map<String, String> map = new HashMap<>();
                    map.put("songId", attr.substring(9).trim());
                    map.put("name", title);
                    songList.add(map);
                }
            }
            for (Map songMap : songList) {
                String songIdUrl = songUrl + songMap.get("songId");
                HtmlUnitUtil.makeHttpRequest(songIdUrl,String.valueOf(songMap.get("name")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
