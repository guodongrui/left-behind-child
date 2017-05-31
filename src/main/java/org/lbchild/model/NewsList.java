package org.lbchild.model;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    ArrayList<NewsItem> newsList;

    public static int countDeleted = 0;
    
    public static int jj = 0;

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
                String s = newsList.get(i).getContent();
                if (s.length() < 20) {
                    list.add(new String(s));
                } else {
                    list.add(new String(s.substring(0, 20)));
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public void deleteIndex(int index) {
        newsList.remove(index);
    }
}
