package com.funyoo.hqxApp.model;

import java.util.Arrays;

public class ImgInfo {
    private Integer error;
    private String[] url;

    public ImgInfo(int i, String[] urls) {
        this.error = i;
        this.url = urls;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImgInfo [error=" + error + ", url=" + Arrays.toString(url) + "]";
    }
}
