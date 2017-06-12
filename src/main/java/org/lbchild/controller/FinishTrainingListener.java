package org.lbchild.controller;

import java.util.HashSet;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.lbchild.window.AddMarksDialog;
import org.lbchild.window.MainWindow;
import org.lbchild.window.ReadMoreWindow;
import org.lbchild.window.TrainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinishTrainingListener implements SelectionListener {
    private static int counter = 0;
    private HashSet<Integer> markedNewsIndex = new HashSet<>();
    private List newsSummaryList;

    private static Logger logger = LoggerFactory.getLogger(FinishTrainingListener.class);

    public FinishTrainingListener(List newsSummaryList) {
        this.newsSummaryList = newsSummaryList;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
    }

    @Override
    public void widgetSelected(SelectionEvent arg0) {
        // 加 TrainWindow 或 ReadMoreWindow 的标记项
        int trainLineId = newsSummaryList.getFocusIndex();
        AddMarksDialog addMarksDialog = AddMarksDialog.getAddMarksDialog();
        int readLineId = trainLineId;
        if (addMarksDialog != null)
            readLineId = addMarksDialog.getLineId();

        markedNewsIndex.add(trainLineId);
        markedNewsIndex.add(readLineId);

        if (markedNewsIndex.size() == 9) {
            logger.info("finish training");
            MessageDialog dialog = new MessageDialog(Display.getCurrent().getActiveShell(), "完成测试", null, "完成测试！",
                    MessageDialog.INFORMATION, new String[] { "OK" }, 0);
            if (dialog.open() == 0) {
                TrainWindow.getTrainWindow().close();
                MainWindow.getMainWindow().getShell().setVisible(true);
                MainWindow.getMainWindow().getShell().setFocus();
            }
            
        }
        logger.info("training set: " + markedNewsIndex.toString());
    }

}
