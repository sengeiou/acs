package com.tifenbao.base.bean.search;

import java.io.Serializable;
import java.util.List;

/**
 * 素质综合评价
 *
 * mar
 * 2019/8/17
 */
public class StudentWeekScoreBean implements Serializable {

    private String score;
    private String rang;
    private String weekname;
    private String begin;
    private String end;
    private List<TypeScoreBean> type_score;

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

    public String getWeekname() {
        return weekname;
    }

    public void setWeekname(String weekname) {
        this.weekname = weekname;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<TypeScoreBean> getType_score() {
        return type_score;
    }

    public void setType_score(List<TypeScoreBean> type_score) {
        this.type_score = type_score;
    }
}
