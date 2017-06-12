package org.lbchild.controller;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllTypesChosenListener implements SelectionListener {

    private static Logger logger = LoggerFactory.getLogger(AllTypesChosenListener.class);
    
    private ArrayList<ArrayList<Button>> btnMarks = new ArrayList<>(); 
    
    public AllTypesChosenListener(ArrayList<ArrayList<Button>> btnMarks) {
        this.btnMarks = btnMarks;
    }
    
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        
        
    }

    @Override
    public void widgetSelected(SelectionEvent arg0) {
        int countMarks = 0;
        for (int i = 0; i < btnMarks.size(); i++) {
            for (int j = 0; j < btnMarks.get(i).size(); j++) {
                if (btnMarks.get(i).get(j).getSelection()) {
                    countMarks++;
                    logger.info("select radio button number: " + countMarks);
                    break;
                }
            } 
        }
        if (countMarks != 10) {
            new MessageDialog(Display.getCurrent().getActiveShell(), "提示", null, "尚有标签未贴！",
                    MessageDialog.INFORMATION, new String[]{"OK"}, 0).open(); 
            countMarks = 0;
            logger.info("show warning: marks num is not 10");
        } else {
            countMarks = 0;
        }
        
    }

}
