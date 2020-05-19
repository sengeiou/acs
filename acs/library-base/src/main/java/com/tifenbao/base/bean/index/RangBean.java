package com.tifenbao.base.bean.index;

import java.io.Serializable;
import java.util.List;

/**
 * 学生分数
 * <p>
 * mar
 * 2019/8/2
 */
public class RangBean implements Serializable {

    private String class_name;
    private String student_name;
    private String student_id;
    private String score;
    private String rang;
    private List<RangTypeBean> type_list;

    private boolean isHead=false;//默认不为头部

    public RangBean(){}

    public RangBean(boolean isHead,List<RangTypeBean> type_list){
        this.isHead=isHead;
        this.type_list=type_list;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public List<RangTypeBean> getType_list() {
        return type_list;
    }

    public void setType_list(List<RangTypeBean> type_list) {
        this.type_list = type_list;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public boolean isHead() {
        return isHead;
    }
}
