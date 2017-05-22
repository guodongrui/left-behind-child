package org.lbchild.window;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.forms.widgets.Section;
import org.hamcrest.Matcher;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.experimental.chart.swt.ChartComposite;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lbchild.chart.LineChart;
import org.swtchart.Chart;
import org.swtchart.ISeries;
import org.swtchart.ISeriesSet;
import org.jfree.chart.JFreeChart;

public class AnalysisWindowTest {
	private static SWTBot bot;
	private static AnalysisWindow analysis;
	private static SWTFormsBot formsBot;

	@BeforeClass
	public static void setupApp() {
		new Thread(new Runnable() {
			public void run() {
				analysis = new AnalysisWindow("src/test/resources/testNewsMark.xml");
				analysis.setBlockOnOpen(true);
				analysis.open();
			}

		}).start();
	}

	@BeforeClass
	public static void setSWTBot() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bot = new SWTBot();
		formsBot = new SWTFormsBot();
	}

	@Test
	public void test() {
		SWTBotPreferences.PLAYBACK_DELAY = 0;

		// Matcher<Section> matcher=org.hamcrest.Matchers;

		getSection("报纸类别");
        
		bot.radio("中央一级").click();
		testJFreeChart(5,1) ;
		bot.radio("省一级").click();
		testJFreeChart(3,2) ;
		bot.radio("经营模式市场化").click();
		getSection("报纸类别");

		getSection("新闻类型");
		bot.radio("纯净新闻").click();
		bot.radio("特稿与特写").click();
		bot.radio("评论").click();
		bot.radio("其他").click();
		getSection("新闻类型");

		getSection("报道主题");
		bot.radio("社会各界帮助关爱").click();
		bot.radio("社会各界对留守儿童现象提出的建议和看法").click();
		bot.radio("表彰帮助关爱留守儿童的单位或个人").click();
		bot.radio("留守儿童遭受暴力").click();
		bot.radio("留守儿童被性侵、猥亵、强奸或是怀孕、生子等").click();
		bot.radio("留守儿童犯罪等").click();
		bot.radio("留守儿童意外死亡").click();
		bot.radio("留守儿童努力上进").click();
		bot.radio("打工父母在城市艰难生活").click();
		bot.radio("其他").click();
		getSection("报道主题");

		getSection("新闻报道来源");
		bot.radio("记者").click();
		bot.radio("政府").click();
		bot.radio("公益团体").click();
		bot.radio("专家学者").click();
		bot.radio("企业").click();
		bot.radio("事业单位").click();
		bot.radio("政府领导、政协或人大代表").click();
		bot.radio("其他").click();
		getSection("新闻报道来源");

	}

	@Test
	public void test2() {
		
		bot.cTabItem("Tendency").activate();

		getSection("报纸类别");

		bot.radio("中央一级").click();
		double man1[] = { 0, 3, 0, 0, 2, 0, 0, 0, 0, 0 };
		double girl1[] = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 };
		testChart(man1, girl1);
		bot.radio("省一级").click();
		double man2[] = { 0, 1, 0, 0, 2, 0, 0, 0, 0, 0 };
		double girl2[] = { 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 };
		testChart(man2, girl2);
		bot.radio("经营模式市场化").click();
		getSection("报纸类别");

		getSection("新闻类型");
		bot.radio("纯净新闻").click();
		bot.radio("特稿与特写").click();
		bot.radio("评论").click();
		bot.radio("其他").click();
		getSection("新闻类型");

		getSection("报道主题");
		bot.radio("社会各界帮助关爱").click();
		bot.radio("社会各界对留守儿童现象提出的建议和看法").click();
		bot.radio("表彰帮助关爱留守儿童的单位或个人").click();
		bot.radio("留守儿童遭受暴力").click();
		bot.radio("留守儿童被性侵、猥亵、强奸或是怀孕、生子等").click();
		bot.radio("留守儿童犯罪等").click();
		bot.radio("留守儿童意外死亡").click();
		bot.radio("留守儿童努力上进").click();
		bot.radio("打工父母在城市艰难生活").click();
		bot.radio("其他").click();
		getSection("报道主题");

		getSection("新闻报道来源");
		bot.radio("记者").click();
		bot.radio("政府").click();
		bot.radio("公益团体").click();
		bot.radio("专家学者").click();
		bot.radio("企业").click();
		bot.radio("事业单位").click();
		bot.radio("政府领导、政协或人大代表").click();
		bot.radio("其他").click();
		getSection("新闻报道来源");
	}

	private void testChart(double[] man, double[] girl) {
		ISeries seriesMan = getLineChart().getSeriesSet().getSeries("男");
		assert (Arrays.equals(seriesMan.getYSeries(), man));
		ISeries seriesGirl = getLineChart().getSeriesSet().getSeries("女");
		assert (Arrays.equals(seriesGirl.getYSeries(), girl));

	}
	private void testJFreeChart(int man,int girl) {
		PiePlot piePlot=(PiePlot)getJFreeChart().getPlot();
		assertEquals (piePlot.getDataset().getValue(0),girl);
		assertEquals(piePlot.getDataset().getValue(1), man);
	
	}
	public static Section getSection(final String name) {
		return UIThreadRunnable.syncExec(new Result<Section>() {

			public Section run() {
				List<? extends Section> sectionList = bot.widgets(WidgetMatcherFactory.widgetOfType(Section.class));
				for (Section s : sectionList) {
					if (name.equals(s.getText())) {
						if (!s.isExpanded()) {
							s.setExpanded(true);

						} else {
							s.setExpanded(false);
						}

					}
				}
				return null;
			}

		});

	}

	public static Chart getLineChart() {
		return UIThreadRunnable.syncExec(new Result<Chart>() {
			public Chart run() {
				Chart chart = bot.widget(WidgetMatcherFactory.widgetOfType(Chart.class));
                
				return chart;
			}
		});
	}
	public static JFreeChart getJFreeChart() {
		return UIThreadRunnable.syncExec(new Result<JFreeChart>() {
			public JFreeChart run() {
				ChartComposite chartComposite = bot.widget(WidgetMatcherFactory.widgetOfType(ChartComposite.class));
               
				return chartComposite.getChart();
			}
		});
	}

}
