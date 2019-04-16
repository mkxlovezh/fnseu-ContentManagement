package com.min.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "news_events_eventlines", schema = "news", catalog = "")
public class NewsEventsEventlines {
    private String newsId;
    private String eventId;
    private String eventlineId;
    private Timestamp time;
    @Id
    @Basic
    @Column(name = "news_id", nullable = false, length = 36)
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    @Basic
    @Column(name = "event_id", nullable = false, length = 36)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "eventline_id", nullable = true, length = 36)
    public String getEventlineId() {
        return eventlineId;
    }

    public void setEventlineId(String eventlineId) {
        this.eventlineId = eventlineId;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsEventsEventlines that = (NewsEventsEventlines) o;

        if (newsId != null ? !newsId.equals(that.newsId) : that.newsId != null) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (eventlineId != null ? !eventlineId.equals(that.eventlineId) : that.eventlineId != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (eventlineId != null ? eventlineId.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
