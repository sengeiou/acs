package com.tifenbao.base.bean.search;

/**
 * 动态
 *
 * mar
 * 2019/8/17
 */
public class StudentScoreBean extends BaseStudentMsgBean {


    private String score_type_1_name;
    private String score_type_2_name;
    private int score;
    private String common;
    private String create_date;
    private String create_user_name;


    public String getScore_type_1_name() {
        return score_type_1_name;
    }

    public void setScore_type_1_name(String score_type_1_name) {
        this.score_type_1_name = score_type_1_name;
    }

    public String getScore_type_2_name() {
        return score_type_2_name;
    }

    public void setScore_type_2_name(String score_type_2_name) {
        this.score_type_2_name = score_type_2_name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }
}
