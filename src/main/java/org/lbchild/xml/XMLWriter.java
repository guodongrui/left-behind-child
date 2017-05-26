package org.lbchild.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.lbchild.model.NewsList;
import org.lbchild.util.Base64Content;

public class XMLWriter {

    private File file;

    public XMLWriter(File file) {
        this.file = file;
    }

    public void insertXml(String newsMarks, String date, int lineId) {

        try {
            // 获取读取xml的对象
            SAXReader sr = new SAXReader();

            // 创建XML文档树
            Document document = sr.read(file);

            // 获得根结点
            Element arrayOfNewsData = document.getRootElement();

            List<Element> list = arrayOfNewsData.elements("NewsData");
            for (Element e : list) {
                if (Integer.parseInt(e.element("LineId").getText()) == lineId) {
                    e.element("NewsMarks").setText(newsMarks);
                    return;
                }
            }

            // 创建结点NewsData
            Element newsData = arrayOfNewsData.addElement("NewsData");

            // 创建NewsData结点下的NewsMark子结点
            Element newsMark = newsData.addElement("NewsMarks");
            newsMark.setText(newsMarks);

            // 创建NewsData结点下的Date子结点
            Element newsDate = newsData.addElement("Date");
            newsDate.setText(date);

            // 创建NewsData结点下的LineId子结点
            Element idElement = newsData.addElement("LineId");
            idElement.setText(String.valueOf(lineId));

            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            format.setLineSeparator("\r\n");
            format.setIndent(true);
            format.setIndent("    ");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
            org.dom4j.io.XMLWriter output = new org.dom4j.io.XMLWriter(bw1, format);
            output.write(document);
            bw1.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateXml(int lineId, String delete) {
        try {
            // 获取读取xml的对象
            SAXReader sr = new SAXReader();

            // 创建XML文档树
            Document document = sr.read(file);

            // 获得根结点
            Element arrayOfNewsData = document.getRootElement();

            List<Element> list = arrayOfNewsData.elements("NewsData");
            list.get(lineId).element("IsDeleted").setText(delete);

            try {
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("utf-8");
                format.setLineSeparator("\r\n");
                format.setIndent(true);
                format.setIndent("    ");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
                org.dom4j.io.XMLWriter output = new org.dom4j.io.XMLWriter(bw1, format);
                output.write(document);
                bw1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEncodedContent(String encodedContent, int selectionId) {
        try {
            // 获取读取xml的对象
            SAXReader sr = new SAXReader();

            // 创建XML文档树
            Document document = sr.read(file);

            // 获得根结点
            Element arrayOfNewsData = document.getRootElement();

            List<Element> list = arrayOfNewsData.elements("NewsData");
            list.get(selectionId).element("EncodedContent").setText(encodedContent);

            try {
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("utf-8");
                format.setLineSeparator("\r\n");
                format.setIndent(true);
                format.setIndent("    ");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
                org.dom4j.io.XMLWriter output = new org.dom4j.io.XMLWriter(bw1, format);
                output.write(document);
                bw1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
