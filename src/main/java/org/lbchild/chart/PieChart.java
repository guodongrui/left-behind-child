package org.lbchild.chart;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
    Map<String, Integer> map = new HashMap<>();
    String title = "";

    public PieChart(Map<String, Integer> map) {
        this.map = map;
    }

    public void setTile(String title) {
        this.title = title;
    }

    public JFreeChart createChart() {
        DefaultPieDataset dpd = new DefaultPieDataset();
        for (Entry<String, Integer> entry : map.entrySet()) {
            dpd.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart(title, dpd, true, true, false);
        chart.getPlot().setBackgroundAlpha(0.0f);
        return chart;
    }
}
