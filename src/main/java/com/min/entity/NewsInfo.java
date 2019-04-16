package com.min.entity;

import org.apache.solr.client.solrj.beans.Field;

public class NewsInfo {
    @Field
    private String id;
    @Field
    private String url;
    @Field
    private String title;
    @Field
    private String authors;
    @Field
    private String time;
    @Field
    private String source;
    @Field
    private String editor;
    @Field
    private String ctype;
    @Field
    private String subtype;
    @Field
    private String keywords;
    @Field
    private String abstr;
    @Field
    private String content;
    @Field
    private String eventId;
    @Field
    private String eventLineId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getEventLineId() {
        return eventLineId;
    }

    public void setEventLineId(String eventLineId) {
        this.eventLineId = eventLineId;
    }

    @Override
    public String toString() {
        return "url:"+url+"  title:"+title+"  authors:"+authors+"  time:"+time+"  source:"+source+"  editor"+editor+"  ctype"+ctype+"  subtype"+subtype+"  keywords"+keywords;
    }
}
