package org.lbchild.controller;

import java.util.ArrayList;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.lbchild.chart.PieChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowOrientationListener implements IExpansionListener {

    private Composite orientation;
    private ArrayList<Map<String, Integer>> newsmarks;
    private int index;
    private static Logger logger = LoggerFactory.getLogger(ShowOrientationListener.class);


    public ShowOrientationListener(ArrayList<Map<String, Integer>> newsmarks, int i, Composite orientation) {
        this.orientation = orientation;
        this.newsmarks = newsmarks;
        index = i;
    }

    @Override
    public void expansionStateChanged(ExpansionEvent e) {

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
        
        logger.info("expansion event");
        
    }

    @Override
    public void expansionStateChanging(ExpansionEvent arg0) {

    }

}

