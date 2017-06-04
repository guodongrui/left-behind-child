package org.lbchild.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class XMLMerger {

    private File file1;

    private File file2;

    public XMLMerger(File file1, File file2) {
        this.file1 = file1;
        this.file2 = file2;
    }

    public void mergeXml() {

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

                    writeIntoXml(file1, document1);
                    j++;
                }
                if (list2.get(j).element("ID").getText().compareTo(list1.get(i).element("ID").getText()) == 0) {
                    list1.get(i).element("NewsMarks").setText(list2.get(j).element("NewsMarks").getText());
                    writeIntoXml(file1, document1);
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

                writeIntoXml(file1, document1);
                j++;
            }

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

    public static void main(String[] args) {
        File file1 = new File("newsmarks.xml");
        File file2 = new File("newsmarks2.xml");
        new XMLMerger(file1, file2).mergeXml();
    }

}
