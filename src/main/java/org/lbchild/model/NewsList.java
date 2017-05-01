package org.lbchild.model;

import java.util.ArrayList;

public class NewsList {
    ArrayList<NewsItem> newsList;

    public ArrayList<NewsItem> getNewsList() {
        return newsList;
    }

    public NewsList(ArrayList<NewsItem> newsList) {
        this.newsList = newsList;
    }

    public NewsItem getNewsItem(int lineId) {
        return newsList.get(lineId);
    }

    public String[] getNewsSummaryList() {
        int n = newsList.size();
        String[] ret = new String[n];
        for (int i = 0; i < n; ++i) {
            ret[i] = new String(newsList.get(i).getContent().substring(0, 20));
        }
        return ret;
    }

}
