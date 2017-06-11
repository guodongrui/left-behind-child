package org.lbchild.controller;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

public class SelectUserListener implements SelectionListener {
    String user;
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        Combo c = (Combo)e.widget;
        user = c.getText();
    }
    
    public String getUser() {
        return user;
    }

}
