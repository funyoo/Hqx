package com.funyoo.hqxApp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hqx")
public class AppConfig {
    private int articleOnceReadNum;
    private String staticUrl;
    private int collectionOnceReadNum;

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public int getArticleOnceReadNum() {
        return articleOnceReadNum;
    }

    public void setArticleOnceReadNum(int articleOnceReadNum) {
        this.articleOnceReadNum = articleOnceReadNum;
    }

    public int getCollectionOnceReadNum() {
        return collectionOnceReadNum;
    }

    public void setCollectionOnceReadNum(int collectionOnceReadNum) {
        this.collectionOnceReadNum = collectionOnceReadNum;
    }
}
