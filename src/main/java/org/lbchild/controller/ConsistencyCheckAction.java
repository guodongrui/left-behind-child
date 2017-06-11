package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.window.ConsistencyCheckDialog;
import org.lbchild.window.TrainWindow;

public class ConsistencyCheckAction extends Action {

    public ConsistencyCheckAction(String string) {
        super();
        setText(string);
    }

    public void run() {
        ConsistencyCheckDialog dialog = new ConsistencyCheckDialog(Display.getCurrent().getActiveShell());
        dialog.open();
    }
}
