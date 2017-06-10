package org.lbchild.model;

import java.util.ArrayList;
import java.util.List;

public class NewsList {

    ArrayList<NewsItem> newsList;

    public NewsList() {
        newsList = new ArrayList<>();
    }

    public void addNewList(ArrayList<NewsItem> newsList) {
        this.newsList.addAll(newsList);
    }

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

    public NewsItem deleteIndex(int index) {
        return newsList.remove(index);
    }

    public void add(int index, NewsItem newsItem) {
        newsList.add(index, newsItem);
    }
}
