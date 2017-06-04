package org.lbchild.controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
import org.lbchild.url.UrlContentDownloader;
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

    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        List list = (List) e.widget;
        
        int id = list.getSelectionIndex(); 
        int sichuanId = id - MainWindow.newsSourceFileLength[MainWindow.Newspaper.NANFANG.ordinal()] - MainWindow.newsSourceFileLength[MainWindow.Newspaper.GUANGMING.ordinal()];
        
        if (newsList.getNewsList().get(id).getLocation().contains("四川日报")) {
            UrlContentDownloader.writeEncodedContent(newsList, sichuanId);
        } else if (newsList.getNewsList().get(id).getLocation().contains("光明日报")) {
            UrlContentDownloader.writeEncodedContent(newsList, id);
        } else if (newsList.getNewsList().get(id).getLocation().contains("南方都市报")) {
        }
         
        
        ReadMoreWindow readmoreWindow = new ReadMoreWindow(newsList, id);
        readmoreWindow.open();
    }
}
