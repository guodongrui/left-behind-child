package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
import org.lbchild.xml.NewXMLFile;
import org.lbchild.xml.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddMarksListener implements SelectionListener {

    private List newsSummaryList;
    private ArrayList<ArrayList<Button>> btnMarks;
    private NewsList newsList;
    private String path;
    private Logger logger = LoggerFactory.getLogger(AddMarksListener.class);
    
    public AddMarksListener(NewsList newsList, List newsSummaryList, ArrayList<ArrayList<Button>> btnMarks,String path) {
        this.newsSummaryList = newsSummaryList;
        this.btnMarks = btnMarks;
        this.newsList = newsList;
        this.path=path;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {

    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        
        logger.info("add marks listener");
        
        String newsMarks = "";
        int pFlag = 0;  // 若为1打印"|"
        for (ArrayList<Button> markGroupType : btnMarks) {
            for (Button btn : markGroupType) {
                if (btn.getSelection()) {
                    if (pFlag == 0) {
                        newsMarks += btn.getText();
                        btn.setSelection(false);
//                        markGroupType.get(0).setSelection(true);
                        pFlag = 1;
                    } else {
                        newsMarks += "|" + btn.getText();
                        btn.setSelection(false);
//                        markGroupType.get(0).setSelection(true);
                    }
                }
            }
        }
        logger.info("finish adding news marks");
        
        File file = new File(path);
        new NewXMLFile(file);
        logger.info("write to file path: " + path);
        XMLWriter out = new XMLWriter(file);

        int lineId = newsSummaryList.getFocusIndex();
        newsSummaryList.setSelection((lineId + 1) % newsSummaryList.getItems().length);
        out.insertXml(newsMarks, newsList.getNewsItem(lineId).getDate().split("-")[0], newsList.getNewsItem(lineId).getId());
        
    }

}
