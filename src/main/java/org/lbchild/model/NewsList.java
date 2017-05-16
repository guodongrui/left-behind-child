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
            String s = newsList.get(i).getContent();
            if (s.length() < 20) {
                ret[i] = new String(s);
            } else {
                ret[i] = new String(s.substring(0, 20));
            }
        }
        return ret;
    }

}
