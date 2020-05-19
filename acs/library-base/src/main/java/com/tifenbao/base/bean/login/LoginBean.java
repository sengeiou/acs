package com.tifenbao.base.bean.login;

import java.io.Serializable;

/**
 * mar
 * 2019/8/4
 */
public class LoginBean implements Serializable {

    private String uid;
    private String school_id;

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
