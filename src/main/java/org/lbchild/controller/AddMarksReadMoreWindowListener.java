package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.lbchild.model.NewsList;
import org.lbchild.window.ReadMoreWindow;
import org.lbchild.xml.XMLWriter;

public class AddMarksReadMoreWindowListener implements SelectionListener {
    
    private ArrayList<ArrayList<Button>> btnMarks;
    private NewsList newsList;

    public AddMarksReadMoreWindowListener(NewsList newsList, ArrayList<ArrayList<Button>> btnMarks) {
        this.btnMarks = btnMarks;
        this.newsList = newsList;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {

    }

    @Override
    public void widgetSelected(SelectionEvent e) {

        ReadMoreWindow win = ReadMoreWindow.getReadMoreWindow();
        
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
        
        File file = new File("src/main/resources/newsmarks.xml");
        XMLWriter out = new XMLWriter(file);
        /*
        int lineId = newsSummaryList.getFocusIndex();
        newsSummaryList.setSelection(lineId + 1);
        */
        out.insertXml(newsMarks, newsList.getNewsItem(win.getLineId()).getDate().split("-")[0], win.getLineId());
        
        
        win.setLineId(win.getLineId() + 1);
        win.setTitle(newsList.getNewsItem(win.getLineId()).getTitle());
        win.setDate("日期：" + newsList.getNewsItem(win.getLineId()).getDate());
        String newsContent = newsList.getNewsItem(win.getLineId()).getContent();
        if (newsContent != null) {
            win.setContent(newsContent);
        }
        
//        ReadMoreWindow window = new ReadMoreWindow(newsList, lineId);
//        setLineId(lineId+1);
//        window.setBlockOnOpen(true);
//        window.open();
//
//        window.close();
        //Display.getCurrent().dispose();
        
    }
    

}
