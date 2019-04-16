package com.min.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventLineInfo {
    private String title;
    private String eventLineId;
    private int passion;
    private int passionNow;
    private String time;
    private ArrayList<EventInfo> eventList;

    public String getEventLineId() {
        return eventLineId;
    }

    public void setEventLineId(String eventLineId) {
        this.eventLineId = eventLineId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPassionNow() {
        return passionNow;
    }

    public void setPassionNow(int passionNow) {
        this.passionNow = passionNow;
    }

    public int getPassion() {
        return passion;
    }

    public void setPassion(int passion) {
        this.passion = passion;
    }

    public ArrayList<EventInfo> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<EventInfo> eventList) {
        this.eventList = eventList;
    }
}
