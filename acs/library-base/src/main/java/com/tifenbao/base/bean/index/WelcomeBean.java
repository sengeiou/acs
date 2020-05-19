package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * mar
 * 2019/9/5
 */
public class WelcomeBean implements Serializable {

    private String id;
    private String device_imei;
    private String title;
    private String common;
    private String create_date;
    private String uid;
    private String school_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevice_imei() {
        return device_imei;
    }

    public void setDevice_imei(String device_imei) {
        this.device_imei = device_imei;
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

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }
}
