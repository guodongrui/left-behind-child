package org.lbchild.model;

public class ConsistencyCheck {
    private int differentLineIndex;
    private String newsMarksType;
    private String user1differentMarks;
    private String user2differentMarks;
    private String title;

    public Integer getDifferentLineIndex() {
        return differentLineIndex;
    }

    public void setDifferentLineIndex(int differentLineIndex) {
        this.differentLineIndex = differentLineIndex;
    }
    
    public String getNewsMarksType() {
        return newsMarksType;
    }

    public void setNewsMarksType(String newsMarksType) {
        this.newsMarksType = newsMarksType;
    }

    public String getUser1differentMarks() {
        return user1differentMarks;
    }

    public void setUser1differentMarks(String user1differentMarks) {
        this.user1differentMarks = user1differentMarks;
    }

    public String getUser2differentMarks() {
        return user2differentMarks;
    }

    public void setUser2differentMarks(String user2differentMarks) {
        this.user2differentMarks = user2differentMarks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
