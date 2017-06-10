package org.lbchild.controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
import org.lbchild.window.ReadMoreWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadMoreListener implements SelectionListener {

    private NewsList newsList;
    private String path;
    private static Logger logger = LoggerFactory.getLogger(ReadMoreListener.class);
    public ReadMoreListener(NewsList newsList, String path) {
        super();
        this.newsList = newsList;
        this.path = path;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {

    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        List list = (List) e.widget;
        
        int id = list.getSelectionIndex(); 
        logger.info("NewsItem selection id: " + id);
        
        ReadMoreWindow readmoreWindow = new ReadMoreWindow(newsList, id, path);
        readmoreWindow.open();
    }
}
