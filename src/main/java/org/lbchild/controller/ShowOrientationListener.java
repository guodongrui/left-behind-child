package org.lbchild.controller;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.lbchild.chart.PieChart;

public class ShowOrientationListener implements IExpansionListener {

    private Composite orientation;
    private ArrayList<Map<String, Integer>> newsmarks;
    private int index;

    public ShowOrientationListener(ArrayList<Map<String, Integer>> newsmarks, int i, Composite orientation) {
        this.orientation = orientation;
        this.newsmarks = newsmarks;
        index = i;
    }

    @Override
    public void expansionStateChanged(ExpansionEvent e) {
        // TODO Auto-generated method stub

        Section s = (Section) e.getSource();

        if (s.isExpanded()) {
            PieChart pieChart = new PieChart(newsmarks.get(index));
            pieChart.setTile(s.getText() + "分析");

            for (Control c : orientation.getChildren()) {
                c.dispose();
            }

            ChartComposite chartOrientationComposite = new ChartComposite(orientation, SWT.NONE, pieChart.createChart(),
                    true);

            orientation.layout(true, true);
        }
    }

    @Override
    public void expansionStateChanging(ExpansionEvent arg0) {
        // TODO Auto-generated method stub

    }

}

