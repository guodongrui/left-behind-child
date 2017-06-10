package org.lbchild.url;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 描述：爬取sichuan.xml文件、guangming.xml中，<TrueUrl>标签下网址的新闻内容
 */
public class UrlAnalyzer {

    public Document getDocument(String url) {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .timeout(10000).ignoreHttpErrors(true).get();
        } catch (UnknownHostException e) {
            System.out.println("连接超时，请检查您的网络！");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (HttpStatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String acceptSiChuanUrl(String url) {

        if (url == null || url.equals("")) {
            return null;
        }

        UrlAnalyzer urlAnalyzer = new UrlAnalyzer();
        Document doc = urlAnalyzer.getDocument(url);
        if (doc == null) {
            return null;
        }

        String newsContent = "";

        // 获取目标HTML代码
        Elements classMain = doc.select("[class=main]");
        Elements classTitle = doc.select("[class=tit3]");

        if (classMain.size() > 0 && classTitle.size() > 0) {
            Elements labelP = classMain.select("p");

            if (labelP.size() > 0) {
                newsContent = labelP.get(0).text();

                // 替换抓取内容中“&nbsp;”变为问号的问题
                try {
                    newsContent = new String(newsContent.getBytes(), "UTF-8").replace('?', ' ').replace('　', ' ');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return newsContent;
        }
        return newsContent;
    }

    public String acceptGuangmingUrl(String url) {

        if (url == null || url.equals("")) {
            return null;
        }

        UrlAnalyzer urlAnalyzer = new UrlAnalyzer();
        Document doc = urlAnalyzer.getDocument(url);
        if (doc == null) {
            return null;
        }

        String newsContent = "";

        // 获取目标HTML代码
        Elements classMain = doc.select("[class=c_c]");

        if (classMain.size() > 0) {
            Elements labelDiv = classMain.select("[id=articleContent]");

            if (labelDiv.size() > 0) {
                newsContent = labelDiv.get(0).text();

                // 替换抓取内容中“&nbsp;”变为问号的问题
                try {
                    newsContent = new String(newsContent.getBytes(), "UTF-8").replace('?', ' ').replace('　', ' ');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // System.out.println(newsContent);
            return newsContent;
        }
        return newsContent;
    }
}
