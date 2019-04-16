package com.min.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Eventlines {
    private String id;
    private String keywords;
    private String entities;
    private Integer eventNum;
    private Integer docNum;

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
    @Column(name = "event_num", nullable = true)
    public Integer getEventNum() {
        return eventNum;
    }

    public void setEventNum(Integer eventNum) {
        this.eventNum = eventNum;
    }

    @Basic
    @Column(name = "doc_num", nullable = true)
    public Integer getDocNum() {
        return docNum;
    }

    public void setDocNum(Integer docNum) {
        this.docNum = docNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eventlines that = (Eventlines) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (keywords != null ? !keywords.equals(that.keywords) : that.keywords != null) return false;
        if (entities != null ? !entities.equals(that.entities) : that.entities != null) return false;
        if (eventNum != null ? !eventNum.equals(that.eventNum) : that.eventNum != null) return false;
        if (docNum != null ? !docNum.equals(that.docNum) : that.docNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (entities != null ? entities.hashCode() : 0);
        result = 31 * result + (eventNum != null ? eventNum.hashCode() : 0);
        result = 31 * result + (docNum != null ? docNum.hashCode() : 0);
        return result;
    }
}
