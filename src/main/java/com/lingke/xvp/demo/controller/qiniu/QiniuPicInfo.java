package com.lingke.xvp.demo.controller.qiniu;

public class QiniuPicInfo {
    public long fsize;
    public String key;
    public String hash;
    public int width;
    public int height;

    public QiniuPicInfo() {
    }

    public long getFsize() {
        return this.fsize;
    }

    public void setFsize(long fsize) {
        this.fsize = fsize;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
