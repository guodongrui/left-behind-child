package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.model.NewsList;
import org.lbchild.window.AddMarksDialog;
import org.lbchild.window.ReadMoreWindow;

public class AddMarksAction extends Action{

    private NewsList newsList;
    
    private int lineId;
    public AddMarksAction(ReadMoreWindow win, int lineId) {
        this.lineId = lineId;
    }
    
    public AddMarksAction(NewsList newsList, int lineId) {
        super();
        this.newsList = newsList;
        this.lineId = lineId;
    }
    
    public void run(){
        AddMarksDialog addmarksDialog = new AddMarksDialog(Display.getCurrent().getActiveShell(), newsList, lineId);
        addmarksDialog.open();
    }
}
