package com.tifenbao.base.bean;

import java.io.Serializable;

/**
 * mar
 * 2019/11/24
 */
public class HttpModeBean implements Serializable {


    private String school_id;
    private String class_id;
    private int mode;

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
