package com.tifenbao.base.bean;

import java.io.Serializable;

/**
 * 打卡上传的数据
 * <p>
 * mar
 * 2019/11/9
 */
public class CheckUpBean implements Serializable {

    private String student_id;
    private String place_id;
    private String time;
    private String temperature;

    public CheckUpBean(String student_id, String class_id, String time,String  temperature) {
        this.student_id = student_id;
        this.place_id = class_id;
        this.time = time;
        this.temperature=temperature;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_id() {
        return place_id;
    }

    public void setClass_id(String class_id) {
        this.place_id = class_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
