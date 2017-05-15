package org.lbchild.controller;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.Section;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.lbchild.chart.PieChart;
import org.lbchild.util.CountLabel;

public class ShowSexOrientationListener implements SelectionListener {

    private Composite orientation;
    private int[][][] sexOrientation;
    public ShowSexOrientationListener(int[][][] sexOrientation, Composite orientation) {
       this.sexOrientation = sexOrientation;
       this.orientation = orientation;
    }
    @Override
    public void widgetDefaultSelected(SelectionEvent arg0) {
        
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        Button b = (Button)e.widget;
        Section section = (Section)b.getParent().getParent();
        int titlePos = CountLabel.countTitlePos(section.getText());
        int marksPos = CountLabel.countLabelPos(titlePos, b.getText());
        
        Map<String, Integer> map = new HashMap();
        
        map.put("男", sexOrientation[titlePos][marksPos][0]);
        map.put("女", sexOrientation[titlePos][marksPos][1]);
        PieChart pieChart = new PieChart(map);
        pieChart.setTile(section.getText() + "" + b.getText() + "性别分布分析");

        for (Control c : orientation.getChildren()) {
            c.dispose();
        }

        ChartComposite chartOrientationComposite = new ChartComposite(orientation, SWT.NONE, pieChart.createChart(),
                true);

        orientation.layout(true, true);
    }

}
