package org.lbchild.url;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.lbchild.model.NewsItem;
import org.lbchild.util.Base64Content;
import org.lbchild.xml.XMLReader;
import org.lbchild.xml.XMLWriter;

public class EncodedContentWriter {

    public static String writeEncodedContent(File file, int selectionId) {

        try {
            XMLReader in = new XMLReader(file);
            ArrayList<Map<String, String>> list = in.readXml();

            String newsContent = "";

            String trueUrl = list.get(selectionId).get("TrueUrl");
            String existEncodedContent = list.get(selectionId).get("EncodedContent");

            if (file.getName().contains("sichuan")) {
                newsContent = new UrlAnalyzer().acceptSiChuanUrl(trueUrl);
            } else if (file.getName().contains("guangming")) {
                newsContent = new UrlAnalyzer().acceptGuangmingUrl(trueUrl);
            }
            
            String encodedContent = "";

            if (newsContent != null) {
                encodedContent = Base64Content.encode(newsContent);
            } else if (existEncodedContent != null) {
                encodedContent = existEncodedContent;
            }

            new XMLWriter(file).updateEncodedContent(encodedContent, selectionId);
            return Base64Content.decode(encodedContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
