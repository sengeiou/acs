package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 考勤统计
 * mar
 * 2019/7/27
 */
public class AttendanceBean implements Serializable {

    private int total_student;
    private int clock_student;
    private int leave_student;
    private int late_student;

    public int getLate_student() {
        return late_student;
    }

    public void setLate_student(int late_student) {
        this.late_student = late_student;
    }

    public int getTotal_student() {
        return total_student;
    }

    public void setTotal_student(int total_student) {
        this.total_student = total_student;
    }

    public int getClock_student() {
        return clock_student;
    }

    public void setClock_student(int clock_student) {
        this.clock_student = clock_student;
    }

    public int getLeave_student() {
        return leave_student;
    }

    public void setLeave_student(int leave_student) {
        this.leave_student = leave_student;
    }
}
