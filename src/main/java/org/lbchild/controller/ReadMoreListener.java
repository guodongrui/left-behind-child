package org.lbchild.controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.lbchild.window.ReadMoreWindow;

public class ReadMoreListener implements SelectionListener {

    public ReadMoreListener() {
        // TODO Auto-generated constructor stub
        super();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void widgetSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub
        ReadMoreWindow readmoreWindow = new ReadMoreWindow();
        readmoreWindow.open();
        
    }
    

}
