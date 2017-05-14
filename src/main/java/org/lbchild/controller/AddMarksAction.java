package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.window.AddMarksDialog;
import org.lbchild.window.DeleteDialog;

public class AddMarksAction extends Action{

    public AddMarksAction() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    public void run(){
        AddMarksDialog addmarksDialog = new AddMarksDialog(Display.getCurrent().getActiveShell());
        addmarksDialog.open();
      
    }
}
