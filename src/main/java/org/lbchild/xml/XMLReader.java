package org.lbchild.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    Element element = null;
    File file = null;
    DocumentBuilder db = null;
    ArrayList<Map<String, String>> newsList = new ArrayList<>();

    DocumentBuilderFactory dbf = null;

    public XMLReader(File file) {
        this.file = file;
    }

    public ArrayList<Map<String, String>> readXml() {

        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            Document dc = db.parse(file);

            element = dc.getDocumentElement();
            NodeList newsNList = element.getChildNodes();
            for (int i = 0; i < newsNList.getLength(); i++) {
                Node newsNode = newsNList.item(i);
                if (Node.ELEMENT_NODE == newsNode.getNodeType() && newsNode.getNodeName().equals("NewsData")) {
                    NodeList nChildList = newsNode.getChildNodes();
                    Map<String, String> map = new HashMap<>();
                    for (int j = 0; j < nChildList.getLength(); j++) {
                        if (Node.ELEMENT_NODE == nChildList.item(j).getNodeType()) {
                            Node childNode = nChildList.item(j);
                            if (null == childNode.getFirstChild()) {
                                continue;
                            }
                            map.put(childNode.getNodeName(), childNode.getFirstChild().getNodeValue());

                        }
                    }
                    newsList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
