package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 *班级评分
 * mar
 * 2019/7/27
 */
public class ScoreBean implements Serializable {

    private String score;
    private String persent;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPersent() {
        return persent;
    }

    public void setPersent(String persent) {
        this.persent = persent;
    }
}
