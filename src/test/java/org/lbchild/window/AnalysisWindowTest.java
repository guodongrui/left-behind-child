package org.lbchild.window;

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.forms.widgets.Section;
import org.hamcrest.Matcher;

import org.junit.BeforeClass;
import org.junit.Test;

public class AnalysisWindowTest {
	private static SWTBot bot;
	private static AnalysisWindow analysis;
	private static SWTFormsBot formsBot;

	@BeforeClass
	public static void setupApp() {
		new Thread(new Runnable() {
			public void run() {
				analysis = new AnalysisWindow();
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
		SWTBotPreferences.PLAYBACK_DELAY = 200;

		// Matcher<Section> matcher=org.hamcrest.Matchers;

		getSection("报纸类别");

		bot.radio("中央一级").click();
		bot.radio("省一级").click();
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

		bot.cTabItem("Tendency").activate();
		getSection("报纸类别");
		bot.radio("中央一级").click();
		bot.radio("省一级").click();
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

}
