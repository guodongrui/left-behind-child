package org.lbchild.controller;

import org.eclipse.jface.action.Action;
import org.lbchild.model.NewsList;
import org.lbchild.window.MainWindow;
import org.lbchild.window.TrainWindow;

public class TrainAction extends Action {
    private NewsList newsList;
    public void run() {
        String[] trainNews = new String[10];
        for (int i = 0; i < trainNews.length; ++i) {
            trainNews[i] = new String(newsList.getNewsList().get(i).getTitle());
        }
        MainWindow.getMainWindow().getShell().setVisible(false);
        TrainWindow trainWindow = new TrainWindow();
        trainWindow.open();
    }
    public TrainAction(String string, NewsList newsList) {
        super();
        setText(string);
        this.newsList = newsList;
    }
}
