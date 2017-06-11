package org.lbchild.controller;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectUserListener implements SelectionListener {
    private static Logger logger = LoggerFactory.getLogger(SelectUserListener.class);
    private String user;
    
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        Combo c = (Combo)e.widget;
        setUser(c.getItem(c.getSelectionIndex()).toString());
        logger.info("combo user is : " + user);
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getUser() {
        return user;
    }

}
