package com.min.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class EventInfo {
    private String title;
    private String eventId;
    private String startTime;
    private String endTime;
    private String keywords;
    private int passion;
    private ArrayList<NewsInfo> NewsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPassion() {
        return passion;
    }

    public void setPassion(int passion) {
        this.passion = passion;
    }

    public ArrayList<NewsInfo> getNewsList() {
        return NewsList;
    }

    public void setNewsList(ArrayList<NewsInfo> newsList) {
        NewsList = newsList;
    }
}
