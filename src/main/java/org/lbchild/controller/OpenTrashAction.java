package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.lbchild.model.NewsList;
import org.lbchild.window.MainWindow;
import org.lbchild.window.TrashDialog;

public class OpenTrashAction extends Action {
    
    private NewsList newsList;

    public void run(){
        TrashDialog dialog = new TrashDialog(MainWindow.getMainWindow().getShell(), newsList);
        dialog.open();
    }
    
    public OpenTrashAction(String str, NewsList newsList){
        super();
        this.newsList = newsList;
        setText(str);
    }
}
