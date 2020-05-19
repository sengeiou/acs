package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 老师列表
 * mar
 * 2019/7/28
 */
public class TeacherBean implements Serializable {

    private String teacher_name;
    private String photo;
    private String comment;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
