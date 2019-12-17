package com.funyoo.hqxApp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CollectionModel {
    private Integer id;
    private Integer aid;
    private String part;
    private String title;
    private String type;
    private String htmlUrl;
    private Date date;
    private Timestamp createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "CollectionModel{" +
                "id=" + id +
                ", aid=" + aid +
                ", part='" + part + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", date=" + date +
                ", createdTime=" + createdTime +
                '}';
    }
}
