package com.min.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class NewsEventsPK implements Serializable {
    private String newsId;
    private String eventId;

    @Column(name = "news_id", nullable = false, length = 36)
    @Id
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Column(name = "event_id", nullable = false, length = 36)
    @Id
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsEventsPK that = (NewsEventsPK) o;

        if (newsId != null ? !newsId.equals(that.newsId) : that.newsId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }
}
