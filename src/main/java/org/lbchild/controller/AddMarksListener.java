package org.lbchild.controller;

import java.util.ArrayList;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.lbchild.model.NewsSummaryList;

public class AddMarksListener implements SelectionListener {

    private static List newsSummaryList;
    private static ArrayList<ArrayList<Button>> btnMarks;

    public AddMarksListener(List newsSummaryList, ArrayList<ArrayList<Button>> btnMarks) {
        this.newsSummaryList = newsSummaryList;
        this.btnMarks = btnMarks;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {

    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        for (ArrayList<Button> markGroupType : btnMarks) {
            for (Button btn : markGroupType) {
                if (btn.getSelection()) {
                    System.out.println("select is: " + btn.getText());
                    btn.setSelection(false);
                    markGroupType.get(0).setSelection(true);
                }
            }
        }
        int i = newsSummaryList.getFocusIndex();
        newsSummaryList.setSelection(i + 1);
    }

}
