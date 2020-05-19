package com.tifenbao.base.bean.signbook;

import java.io.Serializable;

/**
 * 签到簿数据
 * <p>
 * mar
 * 2019/8/21
 */
public class ClockBean implements Serializable {

    private String student_name;
    private String photo;
    private int clock_status;
    private int late_status;
    private int leave_status;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getClock_status() {
        return clock_status;
    }

    public void setClock_status(int clock_status) {
        this.clock_status = clock_status;
    }

    public int getLate_status() {
        return late_status;
    }

    public void setLate_status(int late_status) {
        this.late_status = late_status;
    }

    public int getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(int leave_status) {
        this.leave_status = leave_status;
    }
}
