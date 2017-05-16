package org.lbchild.window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

public class SWTBotTest {
	private static SWTBot bot;
	private static TestWindow win;

	@BeforeClass
	public static void setupApp() {
		new Thread(new Runnable() {
			public void run() {
				win = new TestWindow();
				win.setBlockOnOpen(true);
				win.open();
			}

		}).start();
	}

	@BeforeClass
	public static void setSWTBot() {
	     bot = new SWTBot();
	} 
	
	@Test
	public void test() {
		SWTBotPreferences.PLAYBACK_DELAY = 200; // slow down tests...Otherwise
												// we won't see anything

		SWTBotButton btn = bot.button("确定");
		//btn.setFocus();
		btn.click();
		assert (btn.getText().equals("change..."));
		
	}
}
