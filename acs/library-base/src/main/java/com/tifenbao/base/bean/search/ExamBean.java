package com.tifenbao.base.bean.search;

import java.io.Serializable;

/**
 * 成绩
 * <p>
 * mar
 * 2019/8/17
 */
public class ExamBean implements Serializable {

    private String score;
    private String class_range;
    private String total_range;
    private String exam_name;
    private String subject_name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getClass_range() {
        return class_range;
    }

    public void setClass_range(String class_range) {
        this.class_range = class_range;
    }

    public String getTotal_range() {
        return total_range;
    }

    public void setTotal_range(String total_range) {
        this.total_range = total_range;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
