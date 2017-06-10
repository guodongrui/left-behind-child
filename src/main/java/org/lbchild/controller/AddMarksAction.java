package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.model.NewsList;
import org.lbchild.window.AddMarksDialog;

public class AddMarksAction extends Action{

    private NewsList newsList;
    
    private int lineId;
    private String path;
    
    public AddMarksAction(NewsList newsList, int lineId, String path) {
        super();
        this.newsList = newsList;
        this.lineId = lineId;
        this.path = path;
    }
    
    public void run(){
        AddMarksDialog addmarksDialog = new AddMarksDialog(Display.getCurrent().getActiveShell(), newsList, lineId, path);
        addmarksDialog.open();
    }
}
