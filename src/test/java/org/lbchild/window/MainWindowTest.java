package org.lbchild.window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lbchild.window.MainWindow;

public class MainWindowTest {
    private static SWTBot bot;
    private static MainWindow win;

    @BeforeClass
    public static void setupApp() {
        new Thread(new Runnable() {
            public void run() {
                win = new MainWindow();
                win.setBlockOnOpen(true);
                win.open();
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
    }

    @Test
    public void test() {
        SWTBotPreferences.PLAYBACK_DELAY = 200; // slow down tests...Otherwise
                                                // we won't see anything

        
        SWTBotList lst = bot.list();

        SWTBotRadio btnProvince = bot.radio("省一级");
        SWTBotRadio btnFeature = bot.radio("特稿与特写");
        SWTBotRadio btnAbuse = bot.radio("留守儿童被性侵、猥亵、强奸或是怀孕、生子等");
        SWTBotButton btn = bot.button("Next");
        
        btnProvince.setFocus();
        btnProvince.click();
        btnFeature.click();
        btnAbuse.click();
        btn.click();
        
        assert (lst.selection()[0].equals("小丰为了上网撬开家门，又一次拿家什卖掉换"));

    }
}
