package org.lbchild.controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsList;
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
        List list = (List)e.widget;
        ReadMoreWindow readmoreWindow = new ReadMoreWindow(newsList, list.getSelectionIndex());
        readmoreWindow.open();
        
    }
    

}
