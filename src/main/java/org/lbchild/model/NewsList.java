package org.lbchild.model;

import java.util.ArrayList;
import java.util.List;

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
        List<String> list = new ArrayList<>();
        int n = newsList.size();
        for (int i = 0; i < n; ++i) {
            if (!newsList.get(i).isDeleted()) {
                String s = newsList.get(i).getTitle();
                list.add(new String(s));
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public void deleteIndex(int index) {
        newsList.remove(index);
    }
}
