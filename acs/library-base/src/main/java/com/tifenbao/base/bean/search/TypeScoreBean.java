package com.tifenbao.base.bean.search;

import java.io.Serializable;

/**
 * 单个素质评价
 *
 * mar
 * 2019/8/17
 */
public class TypeScoreBean implements Serializable {

    private String id;
    private String weekname;
    private String student_id;
    private String score_type_id;
    private String score_type_name;
    private String score;
    private String timeline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeekname() {
        return weekname;
    }

    public void setWeekname(String weekname) {
        this.weekname = weekname;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getScore_type_id() {
        return score_type_id;
    }

    public void setScore_type_id(String score_type_id) {
        this.score_type_id = score_type_id;
    }

    public String getScore_type_name() {
        return score_type_name;
    }

    public void setScore_type_name(String score_type_name) {
        this.score_type_name = score_type_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }
}
