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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLMerger {

    public static List<File> directoryList = new ArrayList<>();

    private String path;

    private String userName;

    private static final String NEWSMARKS = "/newsmarks.xml";
    private static Logger logger = LoggerFactory.getLogger(XMLMerger.class);

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
        logger.info("directory list number: " + directoryList.size());
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

            List<Element> listMaster = arrayOfNewsData1.elements("NewsData");
            List<Element> listBranch = arrayOfNewsData2.elements("NewsData");

            Element[] nodeArrayToSort1 = listMaster.toArray(new Element[0]);
            Element[] nodeArrayToSort2 = listBranch.toArray(new Element[0]);

            class NodeComparator implements Comparator<Element> {
                @Override
                public int compare(Element e1, Element e2) {
                    return e1.element("ID").getText().compareTo(e2.element("ID").getText());
                }
            }

            Arrays.sort(nodeArrayToSort1, new NodeComparator());
            Arrays.sort(nodeArrayToSort2, new NodeComparator());
            listMaster = Arrays.asList(nodeArrayToSort1);
            listBranch = Arrays.asList(nodeArrayToSort2);

            logger.info("master size: " + nodeArrayToSort1.length + ", branch size: " + nodeArrayToSort2.length);

            int masterIndex = 0;
            int branchIndex = 0;
            while (masterIndex < listMaster.size() && branchIndex < listBranch.size()) {
                if (listBranch.get(branchIndex).element("ID").getText()
                        .compareTo(listMaster.get(masterIndex).element("ID").getText()) > 0) {
                    masterIndex++;
                } else if (listBranch.get(branchIndex).element("ID").getText()
                        .compareTo(listMaster.get(masterIndex).element("ID").getText()) < 0) {
                    
                    Element newsData = arrayOfNewsData1.addElement("NewsData");

                    // 创建NewsData结点下的NewsMark子结点
                    Element newsMark = newsData.addElement("NewsMarks");
                    newsMark.setText(listBranch.get(branchIndex).element("NewsMarks").getText());

                    // 创建NewsData结点下的Date子结点
                    Element newsDate = newsData.addElement("Date");
                    newsDate.setText(listBranch.get(branchIndex).element("Date").getText());

                    // 创建NewsData结点下的LineId子结点
                    Element idElement = newsData.addElement("ID");
                    idElement.setText(listBranch.get(branchIndex).element("ID").getText());

                    branchIndex++;
                } else {
                    listMaster.get(masterIndex).element("NewsMarks")
                            .setText(listBranch.get(branchIndex).element("NewsMarks").getText());

                    masterIndex++;
                    branchIndex++;
                }
            }

            while (branchIndex < listBranch.size()) {
                Element newsData = arrayOfNewsData1.addElement("NewsData");

                // 创建NewsData结点下的NewsMark子结点
                Element newsMark = newsData.addElement("NewsMarks");
                newsMark.setText(listBranch.get(branchIndex).element("NewsMarks").getText());

                // 创建NewsData结点下的Date子结点
                Element newsDate = newsData.addElement("Date");
                newsDate.setText(listBranch.get(branchIndex).element("Date").getText());

                // 创建NewsData结点下的LineId子结点
                Element idElement = newsData.addElement("ID");
                idElement.setText(listBranch.get(branchIndex).element("ID").getText());

                branchIndex++;
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

    public void mergeAll() {
        for (int i = 0; i < directoryList.size(); i++) {
            mergeXml(new File(path + "/" + userName + NEWSMARKS),
                    new File(path + "/" + directoryList.get(i).getName() + NEWSMARKS));
        }
    }

    public static void main(String[] args) {
        XMLMerger xmlMerger = new XMLMerger("src/main/resources/", "user2");
        xmlMerger.mergeAll();
    }

}
