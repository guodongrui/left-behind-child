package org.lbchild.model;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TrashNewsList {
    private static TrashNewsList trashNewsList;
    private TreeMap<Integer, NewsItem> map;
    

    public void add(Integer index, NewsItem newsItem) {
        map.put(index, newsItem);
    }

    public TrashNewsList() {
        super();
        map = new TreeMap<>();
    }

    public NewsItem remove(Integer index) {
        return map.remove(index);
    }

    public ArrayList<NewsItem> getNewsItems() {
        ArrayList<NewsItem> newsItems = new ArrayList<>();
        for (Entry<Integer, NewsItem> e : map.entrySet()) {
            newsItems.add(e.getValue());
        }
        return newsItems;
    }

    public ArrayList<Integer> getIndices() {
        ArrayList<Integer> indices = new ArrayList<>();
        for (Entry<Integer, NewsItem> e : map.entrySet()) {
            indices.add(e.getKey());
        }
        return indices;
    }

    public static TrashNewsList getInstance() {
        if (trashNewsList == null)
            trashNewsList = new TrashNewsList();
        
        return trashNewsList;
    }
   
}