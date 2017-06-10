package org.lbchild.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.lbchild.model.User;
import org.lbchild.window.MainWindow;

public class LogoutAction extends Action {
    public LogoutAction(String string) {
        super();
        setText(string);
    }

    public void run() {
        try {
            PrintWriter  out = new PrintWriter(new FileWriter("src/main/resources/user.txt"));
            out.write("");
            out.close();
            User.getInstance().setUserName("");
            Display.getCurrent().dispose();
            MainWindow.main(null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
