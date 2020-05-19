package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 课程表单独课程
 * mar
 * 2019/7/27
 */
public class CourseBean implements Serializable {

    private String id;
    private String school_id;
    private String class_id;
    private String weekday;
    private String course_time_id;
    private String course_name;
    private String uid;
    private String create_date;
    private String classroom_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getCourse_time_id() {
        return course_time_id;
    }

    public void setCourse_time_id(String course_time_id) {
        this.course_time_id = course_time_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getClassroom_name() {
        return classroom_name;
    }

    public void setClassroom_name(String classroom_name) {
        this.classroom_name = classroom_name;
    }
}
