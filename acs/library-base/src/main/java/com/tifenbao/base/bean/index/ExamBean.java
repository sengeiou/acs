package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * mar
 * 2019/9/6
 */
public class ExamBean implements Serializable {

    private String id;
    private String room_name;
    private String subject;
    private String teacher;
    private String exam_time;
    private String common;


    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
