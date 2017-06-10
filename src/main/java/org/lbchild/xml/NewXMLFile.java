package org.lbchild.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewXMLFile {
    private File file;
    private static Logger logger = LoggerFactory.getLogger(NewXMLFile.class);
    
    public NewXMLFile(File file) {
        this.file = file;
        initializeFile();
    }

    public boolean hasNewsData() {
        try {
            // 获取读取xml的对象
            SAXReader sr = new SAXReader();

            // 创建XML文档树
            Document document = sr.read(file);

            // 获得根结点
            Element arrayOfNewsData = document.getRootElement();

            Element newsData = arrayOfNewsData.element("NewsData");

            if (newsData == null) {
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void initializeFile() {
        try {
            if (!file.exists()) {
                PrintWriter out = new PrintWriter(new FileWriter(file));
                out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n\n" + "<ArrayOfNewsData>\n"
                        + "</ArrayOfNewsData>");
                out.close();
                logger.info("initialize xml file stored data of news");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
