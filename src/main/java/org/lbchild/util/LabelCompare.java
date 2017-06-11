package org.lbchild.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lbchild.model.ConsistencyCheck;
import org.lbchild.model.NewsList;
import org.lbchild.xml.XMLReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabelCompare {
    
    private List<Integer> diflist;
    private ArrayList<String> labelList1;
    private ArrayList<String> labelList2;
    private NewsList newsList;
    private List<ConsistencyCheck> consistencyCheckTable;
 
    private static Logger logger = LoggerFactory.getLogger(LabelCompare.class);
    
    public LabelCompare(NewsList newsList, String user1path, String user2path) {
        this.newsList = newsList;
        compare(user1path, user2path);
        for (int i = 0; i < diflist.size(); ++i) {
            ConsistencyCheck c = new ConsistencyCheck();
            int index = diflist.get(i);
            c.setDifferentLineIndex(index);
            c.setUser1differentMarks(labelList1.get(index));
            c.setUser2differentMarks(labelList2.get(index));
            c.setTitle(newsList.getNewsItem((index/9)).getTitle());
            consistencyCheckTable.add(c);
        }
    }
    
    
    public List<ConsistencyCheck> getConsistencyCheckTable() {
        return consistencyCheckTable;
    }


    private void compare(String user1path, String user2path) {
        File user1 = new File(user1path);
        File user2 = new File(user2path);
        XMLReader in1 = new XMLReader(user1);
        XMLReader in2 = new XMLReader(user2);
        ArrayList<Map<String, String>> user1List = in1.readXml();
        labelList1 = new ArrayList<>();
        ArrayList<Map<String, String>> user2List = in2.readXml();
        labelList2 = new ArrayList<>();
        for (int i = 0; i < user1List.size(); i++) {

            String user1Name = user1path.replace("src/main/resources/", "").replaceAll("/.*", "");
            String user2Name = user2path.replace("src/main/resources/", "").replaceAll("/.*", "");
            logger.info("compare user1: " + user1Name + ", compare user2: " + user2Name);
            
            String user1marks = Crypt.decryptContent(user1List.get(i).get("NewsMarks"), user1Name);
            String user2marks = Crypt.decryptContent(user2List.get(i).get("NewsMarks"), user2Name);
            
            labelToList(user1marks, labelList1);
            labelToList(user2marks, labelList2);
            
            diflist = compareLabelList(labelList1, labelList2);
            
        }

    }

    private static List<Integer> compareLabelList(List<String> list1, List<String> list2) {
        List<Integer> difList = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                difList.add(i);
            }

        }
        return difList;
    }

    private void labelToList(String label, ArrayList<String> labelList) {
        if (label.length() < 1) {
            return;
        }

        int preIndex = 0;
        int currentIndex = 0;
        while ((currentIndex = label.indexOf("|", preIndex)) != -1) {

            labelList.add(label.substring(preIndex, currentIndex));
            preIndex = currentIndex + 1;
        }
        labelList.add(label.substring(preIndex));

    }
    
    public double getRate() {
        return (1 - (double)diflist.size() / labelList1.size()) * 100;
    }
    
    public NewsList getNewsList() {
        return newsList;
    }
    
    public static void main(String[] args) {
//        LabelCompare lc = new LabelCompare();
//        lc.compare("src/main/resources/guodongrui/training.xml", "src/main/resources/admin/training.xml");
    }
}
