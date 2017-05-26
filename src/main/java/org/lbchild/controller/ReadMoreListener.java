package org.lbchild.controller;

import java.io.File;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
import org.lbchild.url.EncodedContentWriter;
import org.lbchild.window.MainWindow;
import org.lbchild.window.ReadMoreWindow;

public class ReadMoreListener implements SelectionListener {

    private NewsList newsList;

    public ReadMoreListener(NewsList newsList) {
        // TODO Auto-generated constructor stub
        super();
        this.newsList = newsList;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        // TODO Auto-generated method stub
        List list = (List) e.widget;
        int id = list.getSelectionIndex();
        int guangmingId = id - MainWindow.sichuanLength - MainWindow.nanfangLength;
        String content = null;
        if (newsList.getNewsList().get(id).getLocation().contains("四川日报")) {
            content = EncodedContentWriter.writeEncodedContent(new File("src/main/resources/sichuan2.xml"), id);
            newsList.getNewsList().get(id).setContent(content);
        } else if (newsList.getNewsList().get(id).getLocation().contains("光明日报")) {
            content = EncodedContentWriter.writeEncodedContent(new File("src/main/resources/guangming2.xml"), guangmingId);
            newsList.getNewsList().get(id).setContent(content);
        }
        
        ReadMoreWindow readmoreWindow = new ReadMoreWindow(newsList, id);
        readmoreWindow.open();
    }
}
