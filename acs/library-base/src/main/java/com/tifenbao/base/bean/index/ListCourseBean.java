package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 一个时间段内的一周课表
 * <p>
 * mar
 * 2019/7/27
 */
public class ListCourseBean implements Serializable {
    private String id;
    private String school_id;
    private String oures_name;
    private String course_begin;
    private String course_end;
    private String uid;
    private String create_date;
    private CourseListBean course_list;

    private boolean isHead = false;//是否头部,默认不是头部

    public ListCourseBean() {
    }


    public ListCourseBean(boolean isHead) {
        this.isHead = isHead;
    }

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

    public String getOures_name() {
        return oures_name;
    }

    public void setOures_name(String oures_name) {
        this.oures_name = oures_name;
    }

    public String getCourse_begin() {
        return course_begin;
    }

    public void setCourse_begin(String course_begin) {
        this.course_begin = course_begin;
    }

    public String getCourse_end() {
        return course_end;
    }

    public void setCourse_end(String course_end) {
        this.course_end = course_end;
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

    public CourseListBean getCourse_list() {
        return course_list;
    }

    public void setCourse_list(CourseListBean course_list) {
        this.course_list = course_list;
    }

    public boolean isHead() {
        return isHead;
    }
}
