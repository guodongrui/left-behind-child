package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.lbchild.model.User;
import org.lbchild.window.MainWindow;
import org.lbchild.xml.XMLMerger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MergeAction extends Action {
    private static Logger logger = LoggerFactory.getLogger(MergeAction.class);
    public void run() {
        XMLMerger xmlMerger = new XMLMerger("src/main/resources/", User.getInstance().getUserName());
        logger.info("admin: " + User.getInstance().getUserName());
        xmlMerger.mergeAll();
        logger.info("merge all files");
        new MessageDialog(MainWindow.getMainWindow().getShell(), "合并", null, "合并成功",
                MessageDialog.INFORMATION, new String[]{"OK"}, 0).open();
    }

    public MergeAction(String str) {
        super();
        setText(str);
    }
}
