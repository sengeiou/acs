package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 相册图库
 * mar
 * 2019/7/28
 */
public class GalleryBean implements Serializable {

    private String id;
    private String group_name;
    private String cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
