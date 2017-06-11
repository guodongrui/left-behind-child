package org.lbchild.controller;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.lbchild.model.NewsList;
import org.lbchild.window.ReadMoreWindow;
import org.lbchild.xml.NewXMLFile;
import org.lbchild.xml.XMLWriter;

public class AddMarksReadMoreWindowListener implements SelectionListener {
    
    private ArrayList<ArrayList<Button>> btnMarks;
    private NewsList newsList;
    private String path;

    public AddMarksReadMoreWindowListener(NewsList newsList, ArrayList<ArrayList<Button>> btnMarks, String path) {
        this.btnMarks = btnMarks;
        this.newsList = newsList;
        this.path = path;
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
        
        File file = new File(path);
        new NewXMLFile(file);
        XMLWriter out = new XMLWriter(file);
        
        win.setLineId((win.getLineId() + 1) % newsList.getNewsSummaryList().length);
        win.setTitle(newsList.getNewsItem(win.getLineId()).getTitle());
        win.setDate("日期：" + newsList.getNewsItem(win.getLineId()).getDate());
        String newsContent = newsList.getNewsItem(win.getLineId()).getContent();
        if (newsContent != null) {
            win.setContent(newsContent);
        }
        
        out.insertXml(newsMarks, newsList.getNewsItem(win.getLineId()).getDate().split("-")[0], newsList.getNewsItem(win.getLineId()).getId());
        
    }
}
