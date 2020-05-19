package com.tifenbao.base.bean.card;

import java.io.Serializable;
import java.util.List;

/**
 * 打卡信息
 * <p>
 * mar
 * 2019/8/18
 */
public class CheckInBean implements Serializable {

    private String flag;//刷卡状态status，duty：值日生考勤，调起评分弹窗，attendance：学生考勤，调起弹窗拍照考勤，normal：普通打卡，调起个人信息弹窗评分：

    private String status;//值日生：'status'=>'N'   考勤：status：ok 正常入校，late 迟到 ，leave 离校

    private List<FormBean> form;//值日生的数据

    private CardStudentBean student;//普通打卡学生数据

    private ScoreBean score;//普通打卡学生考试数据

    private String clock_id;//打卡学生id;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FormBean> getForm() {
        return form;
    }

    public void setForm(List<FormBean> form) {
        this.form = form;
    }

    public CardStudentBean getStudent() {
        return student;
    }

    public void setStudent(CardStudentBean student) {
        this.student = student;
    }

    public ScoreBean getScore() {
        return score;
    }

    public void setScore(ScoreBean score) {
        this.score = score;
    }

    public String getClock_id() {
        return clock_id;
    }

    public void setClock_id(String clock_id) {
        this.clock_id = clock_id;
    }
}
