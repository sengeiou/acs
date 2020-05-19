package com.tifenbao.base.bean.index;

import java.io.Serializable;
import java.util.List;

/**
 * mar
 * 2019/8/2
 */
public class PracticalBean implements Serializable {

    private String id;
    private String school_id;
    private String class_id;
    private String class_name;
    private String student_id;
    private String student_name;
    private String student_number;
    private String content;
    private String witness;
    private String witness_tel;
    private String timeline;
    private String status;
    private String score_type_1;
    private String score_type_1_name;
    private String score_type_2;
    private String score_type_2_name;
    private String score;
    private String reply_uid;
    private String reply_content;
    private String reply_date;
    private List<ImageBean> image_list;

    private boolean isSelect=false;//默认没被选中

    public PracticalBean(){}

    public PracticalBean(boolean isSelect){
        this.isSelect=isSelect;
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

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getWitness_tel() {
        return witness_tel;
    }

    public void setWitness_tel(String witness_tel) {
        this.witness_tel = witness_tel;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore_type_1() {
        return score_type_1;
    }

    public void setScore_type_1(String score_type_1) {
        this.score_type_1 = score_type_1;
    }

    public String getScore_type_1_name() {
        return score_type_1_name;
    }

    public void setScore_type_1_name(String score_type_1_name) {
        this.score_type_1_name = score_type_1_name;
    }

    public String getScore_type_2() {
        return score_type_2;
    }

    public void setScore_type_2(String score_type_2) {
        this.score_type_2 = score_type_2;
    }

    public String getScore_type_2_name() {
        return score_type_2_name;
    }

    public void setScore_type_2_name(String score_type_2_name) {
        this.score_type_2_name = score_type_2_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReply_uid() {
        return reply_uid;
    }

    public void setReply_uid(String reply_uid) {
        this.reply_uid = reply_uid;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public String getReply_date() {
        return reply_date;
    }

    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }

    public List<ImageBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageBean> image_list) {
        this.image_list = image_list;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
