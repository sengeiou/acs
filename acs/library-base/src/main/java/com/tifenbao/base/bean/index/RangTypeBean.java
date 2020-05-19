package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 学生科目分数
 *
 * mar
 * 2019/8/2
 */
public class RangTypeBean implements Serializable {

    private String score_type_name;
    private String score;

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
}
