package org.lbchild.controller;



import java.util.ArrayList;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;

public class ExpandCleanMarksListener implements IExpansionListener {
    private ArrayList<Button> newsmarks;
    public ExpandCleanMarksListener(ArrayList<Button> newsmarks) {
        this.newsmarks = newsmarks;
    }
    @Override
    public void expansionStateChanged(ExpansionEvent arg0) {
        for (Button button: newsmarks) {
            button.setSelection(false);
        }
    }

    @Override
    public void expansionStateChanging(ExpansionEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
