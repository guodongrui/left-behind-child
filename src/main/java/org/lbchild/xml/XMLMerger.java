package org.lbchild.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class XMLMerger {

    public static List<File> directoryList = new ArrayList<>();

    private String path;
    
    private String userName;
    
    private static final String NEWSMARKS = "/newsmarks.xml";

    public XMLMerger(String path, String userName) {
        this.setPath(path);
        this.setUserName(userName);
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isDirectory()) {
                directoryList.add(tempList[i]);
            }
        }
    }

    public void mergeXml(File file1, File file2) {

        try {
            // 获取读取xml的对象
            SAXReader sr = new SAXReader();

            // 创建XML文档树
            Document document1 = sr.read(file1);
            Document document2 = sr.read(file2);

            // 获得根结点
            Element arrayOfNewsData1 = document1.getRootElement();
            Element arrayOfNewsData2 = document2.getRootElement();

            List<Element> list1 = arrayOfNewsData1.elements("NewsData");
            List<Element> list2 = arrayOfNewsData2.elements("NewsData");

            Element[] nodeArrayToSort1 = list1.toArray(new Element[0]);
            Element[] nodeArrayToSort2 = list2.toArray(new Element[0]);

            class NodeComparator implements Comparator<Element> {
                @Override
                public int compare(Element e1, Element e2) {
                    return e1.element("ID").getText().compareTo(e2.element("ID").getText());
                }
            }

            Arrays.sort(nodeArrayToSort1, new NodeComparator());
            Arrays.sort(nodeArrayToSort2, new NodeComparator());
            list1 = Arrays.asList(nodeArrayToSort1);
            list2 = Arrays.asList(nodeArrayToSort2);

            int i = 0;
            int j = 0;
            while (i < list1.size() && j < list2.size()) {
                if (list2.get(j).element("ID").getText().compareTo(list1.get(i).element("ID").getText()) > 0) {
                    i++;
                }
                if (list2.get(j).element("ID").getText().compareTo(list1.get(i).element("ID").getText()) < 0) {
                    Element newsData = arrayOfNewsData1.addElement("NewsData");

                    // 创建NewsData结点下的NewsMark子结点
                    Element newsMark = newsData.addElement("NewsMarks");
                    newsMark.setText(list2.get(j).element("NewsMarks").getText());

                    // 创建NewsData结点下的Date子结点
                    Element newsDate = newsData.addElement("Date");
                    newsDate.setText(list2.get(j).element("Date").getText());

                    // 创建NewsData结点下的LineId子结点
                    Element idElement = newsData.addElement("ID");
                    idElement.setText(list2.get(j).element("ID").getText());

                    j++;
                }
                if (list2.get(j).element("ID").getText().compareTo(list1.get(i).element("ID").getText()) == 0) {
                    list1.get(i).element("NewsMarks").setText(list2.get(j).element("NewsMarks").getText());

                    i++;
                    j++;
                }
            }

            while (j < list2.size()) {
                Element newsData = arrayOfNewsData1.addElement("NewsData");

                // 创建NewsData结点下的NewsMark子结点
                Element newsMark = newsData.addElement("NewsMarks");
                newsMark.setText(list2.get(j).element("NewsMarks").getText());

                // 创建NewsData结点下的Date子结点
                Element newsDate = newsData.addElement("Date");
                newsDate.setText(list2.get(j).element("Date").getText());

                // 创建NewsData结点下的LineId子结点
                Element idElement = newsData.addElement("ID");
                idElement.setText(list2.get(j).element("ID").getText());

                j++;
            }
            writeIntoXml(file1, document1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeIntoXml(File file, Document document) {
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
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public static void main(String[] args) {
        XMLMerger xmlMerger = new XMLMerger("src/main/resources/", "user2");
        for (int i = 0; i < directoryList.size(); i++) {
            xmlMerger.mergeXml(
                    new File(xmlMerger.getPath() + "/" + xmlMerger.getUserName() + NEWSMARKS), 
                    new File(xmlMerger.getPath() + directoryList.get(i).getName() + NEWSMARKS));
        }
    }

}
