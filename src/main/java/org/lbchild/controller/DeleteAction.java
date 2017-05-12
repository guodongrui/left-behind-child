package org.lbchild.controller;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.window.DeleteDialog;


public class DeleteAction extends Action{

    public DeleteAction() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    public void run(){
        DeleteDialog deleteDialog = new DeleteDialog(Display.getCurrent().getActiveShell());
        deleteDialog.open();
      
    }
}
