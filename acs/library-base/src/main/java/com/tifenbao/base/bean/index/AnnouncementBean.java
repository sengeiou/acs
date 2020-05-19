package com.tifenbao.base.bean.index;

import java.io.Serializable;
import java.util.List;

/**
 * 公告公示
 * mar
 * 2019/7/28
 */
public class AnnouncementBean implements Serializable {

    private String id;
    private String title;

    private List<ClassBean> class_list;
    private List<PracticalBean> practical;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ClassBean> getClass_list() {
        return class_list;
    }

    public void setClass_list(List<ClassBean> class_list) {
        this.class_list = class_list;
    }

    public List<PracticalBean> getPractical() {
        return practical;
    }

    public void setPractical(List<PracticalBean> practical) {
        this.practical = practical;
    }
}
