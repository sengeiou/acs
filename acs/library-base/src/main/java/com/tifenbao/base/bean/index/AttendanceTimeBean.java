package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 考勤规则
 * <p>
 * mar
 * 2019/7/28
 */
public class AttendanceTimeBean implements Serializable {

    private String morning_come;
    private String morning_leave;
    private String afternoon_come;
    private String afternoon_leave;
    private int duration;

    public String getMorning_come() {
        return morning_come;
    }

    public void setMorning_come(String morning_come) {
        this.morning_come = morning_come;
    }

    public String getMorning_leave() {
        return morning_leave;
    }

    public void setMorning_leave(String morning_leave) {
        this.morning_leave = morning_leave;
    }

    public String getAfternoon_come() {
        return afternoon_come;
    }

    public void setAfternoon_come(String afternoon_come) {
        this.afternoon_come = afternoon_come;
    }

    public String getAfternoon_leave() {
        return afternoon_leave;
    }

    public void setAfternoon_leave(String afternoon_leave) {
        this.afternoon_leave = afternoon_leave;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
