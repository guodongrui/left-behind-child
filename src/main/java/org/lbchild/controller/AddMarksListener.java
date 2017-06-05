package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
import org.lbchild.model.NewsSummaryList;
import org.lbchild.xml.XMLWriter;

public class AddMarksListener implements SelectionListener {

    private List newsSummaryList;
    private ArrayList<ArrayList<Button>> btnMarks;
    private NewsList newsList;
    private String path;
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

        String newsMarks = "";
        int pFlag = 0;  // 若为1打印"|"
        for (ArrayList<Button> markGroupType : btnMarks) {
            for (Button btn : markGroupType) {
                if (btn.getSelection()) {
                    if (pFlag == 0) {
                        newsMarks += btn.getText();
                        btn.setSelection(false);
                        markGroupType.get(0).setSelection(true);
                        pFlag = 1;
                    } else {
                        newsMarks += "|" + btn.getText();
                        btn.setSelection(false);
                        markGroupType.get(0).setSelection(true);
                    }
                }
            }
        }
        
        File file = new File(path);
        XMLWriter out = new XMLWriter(file);

        int lineId = newsSummaryList.getFocusIndex();
        newsSummaryList.setSelection(lineId + 1);
        out.insertXml(newsMarks, newsList.getNewsItem(lineId).getDate().split("-")[0], newsList.getNewsItem(lineId).getId());
        
    }

}
