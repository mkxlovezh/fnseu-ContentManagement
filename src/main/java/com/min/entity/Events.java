package com.min.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Events {
    private String id;
    private String keywords;
    private String entities;
    private Integer docNum;
    private Timestamp time;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "keywords", nullable = false, length = -1)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "entities", nullable = false, length = -1)
    public String getEntities() {
        return entities;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }

    @Basic
    @Column(name = "doc_num", nullable = true)
    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
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

        Events events = (Events) o;

        if (id != null ? !id.equals(events.id) : events.id != null) return false;
        if (keywords != null ? !keywords.equals(events.keywords) : events.keywords != null) return false;
        if (entities != null ? !entities.equals(events.entities) : events.entities != null) return false;
        if (docNum != null ? !docNum.equals(events.docNum) : events.docNum != null) return false;
        if (time != null ? !time.equals(events.time) : events.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (entities != null ? entities.hashCode() : 0);
        result = 31 * result + (docNum != null ? docNum.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
