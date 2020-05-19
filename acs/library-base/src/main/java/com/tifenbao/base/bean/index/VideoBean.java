package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 视频
 *
 * mar
 * 2019/7/28
 */
public class VideoBean implements Serializable {

    private String filepath;
    private String title;
    private String common;
    private String cover;


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
