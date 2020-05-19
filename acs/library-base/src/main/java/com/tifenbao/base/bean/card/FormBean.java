package com.tifenbao.base.bean.card;

import java.io.Serializable;

/**
 * 值日生评分信息
 * <p>
 * mar
 * 2019/8/18
 */
public class FormBean implements Serializable {

    private String id;
    private String score_type_name;
    private int base_score;
    private int score;
    private String rage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore_type_name() {
        return score_type_name;
    }

    public void setScore_type_name(String score_type_name) {
        this.score_type_name = score_type_name;
    }

    public int getBase_score() {
        return base_score;
    }

    public void setBase_score(int base_score) {
        this.base_score = base_score;
    }

    public String getRage() {
        return rage;
    }

    public void setRage(String rage) {
        this.rage = rage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
