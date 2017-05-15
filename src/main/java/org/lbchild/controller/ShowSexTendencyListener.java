package org.lbchild.controller;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.Section;
import org.lbchild.chart.LineChart;
import org.lbchild.util.CountLabel;

public class ShowSexTendencyListener implements SelectionListener {

    private Composite tendencyLineChartComposite;
    private double[][][][] sexTendencyCount;
    public ShowSexTendencyListener(double[][][][] sexTendencyCount, Composite tendencyLineChartComposite) {
        this.tendencyLineChartComposite = tendencyLineChartComposite;
        this.sexTendencyCount = sexTendencyCount;
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
        LineChart lineChart = new LineChart(sexTendencyCount[titlePos][marksPos][0], sexTendencyCount[titlePos][marksPos][1]);
        lineChart.setTitle(section.getText() + b.getText() + "性别趋势分析");
        
        for (Control c : tendencyLineChartComposite.getChildren()) {
            c.dispose();
        }
        
        lineChart.createLineChart(tendencyLineChartComposite);
        tendencyLineChartComposite.layout();
    }

}
