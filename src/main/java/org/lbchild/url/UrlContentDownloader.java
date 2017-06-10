package org.lbchild.url;

import org.lbchild.model.NewsList;
import org.lbchild.util.Base64Content;
import org.lbchild.xml.XMLSelectionIdWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlContentDownloader {
    private static Logger logger = LoggerFactory.getLogger(UrlContentDownloader.class);
    public static void writeEncodedContent(NewsList newsList, int selectionId) {

        try {
            String newsContent = "";
            
            // 两个文件 sichuan.xml 和 guangming.xml 要爬取 URL 的内容
            String content = newsList.getNewsList().get(selectionId).getContent();
            String fileName = null;
            if (content.startsWith("http://epaper.scdaily.cn")) {
                newsContent = new UrlAnalyzer().acceptSiChuanUrl(content);
                newsList.getNewsList().get(selectionId).setContent(newsContent);
                fileName = "src/main/resources/sichuan2.xml";
            } else if (content.startsWith("http://epaper.gmw.cn")) {
                newsContent = new UrlAnalyzer().acceptGuangmingUrl(content);
                newsList.getNewsList().get(selectionId).setContent(newsContent);
                fileName = "src/main/resources/guangming2.xml";
            } else {}
          
            logger.info("set selectionId: " + selectionId + " content which from url");
            // 将爬取的内容加密写入新闻文件 xml 中
            String encodedContent = null;

            if (newsContent != null) {
                encodedContent = Base64Content.encode(newsContent);
            }
            
            if (fileName != null && encodedContent != null) {
                new XMLSelectionIdWriter().updateEncodedContent(encodedContent, selectionId);
                logger.info("download selectionId: " + selectionId + " into file");

            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
