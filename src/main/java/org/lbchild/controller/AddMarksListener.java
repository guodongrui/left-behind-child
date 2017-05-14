package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsSummaryList;
import org.lbchild.xml.XMLWriter;

public class AddMarksListener implements SelectionListener {

    private static List newsSummaryList;
    private static ArrayList<ArrayList<Button>> btnMarks;

    public AddMarksListener(List newsSummaryList, ArrayList<ArrayList<Button>> btnMarks) {
        this.newsSummaryList = newsSummaryList;
        this.btnMarks = btnMarks;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {

    }

    @Override
    public void widgetSelected(SelectionEvent e) {

        String newsMarks = "";
        int pFlag = 0;
        for (ArrayList<Button> markGroupType : btnMarks) {
            for (Button btn : markGroupType) {
                if (btn.getSelection()) {
                    if (pFlag == 0) {
                        newsMarks += btn.getText();
                        pFlag = 1;
                    } else {
                        newsMarks += "|" + btn.getText();
                        btn.setSelection(false);
                        markGroupType.get(0).setSelection(true);
                    }
                }
            }
        }
        
        File file = new File("src/main/resources/newsmarks.xml");
        XMLWriter out = new XMLWriter(file);

        int lineId = newsSummaryList.getFocusIndex();
        newsSummaryList.setSelection(lineId + 1);
        out.insertXml(newsMarks, lineId);
        
    }

}
