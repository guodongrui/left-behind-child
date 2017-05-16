package org.lbchild.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.Range;

/**
 * An example for area chart.
 */
public class LineChart {

    private double[] y1s;
    private double[] y2s;
    private double[] year = { 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016 };

    private String title = "";

    public LineChart(double[] y1s, double[] y2s) {

        this.y1s = y1s;
        this.y2s = y2s;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createLineChart(Composite parent) {
        Chart chart = new Chart(parent, SWT.NONE);

        // set titles
        chart.getTitle().setText(title);
        chart.getAxisSet().getXAxis(0).getTitle().setText("时间");
        chart.getAxisSet().getYAxis(0).getTitle().setText("新闻数");

        // create line series
        ILineSeries line1 = (ILineSeries) chart.getSeriesSet().createSeries(SeriesType.LINE, "男");

        line1.setYSeries(y1s);
        line1.setXSeries(year);

        ILineSeries line2 = (ILineSeries) chart.getSeriesSet().createSeries(SeriesType.LINE, "女");
        line2.setLineColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
        line2.setYSeries(y2s);
        line2.setXSeries(year);

        // adjust the axis range
        chart.getAxisSet().adjustRange();

        //chart.getAxisSet().getYAxis(0).zoomOut(5);
        chart.getAxisSet().getXAxis(0).setRange(new Range(2006, 2016));
        

    }
}