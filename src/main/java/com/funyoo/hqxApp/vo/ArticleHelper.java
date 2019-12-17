package com.funyoo.hqxApp.vo;

public class ArticleHelper {
    private String part;
    private Integer id;

    public ArticleHelper(String part, Integer id) {
        this.part = part;
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
