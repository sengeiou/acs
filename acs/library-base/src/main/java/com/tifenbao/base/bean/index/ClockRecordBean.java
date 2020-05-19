package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * mar
 * 2019/8/30
 */
public class ClockRecordBean implements Serializable {

    private String clock_time;
    private String class_name;
    private String student_name;

    public ClockRecordBean() {
    }

    public ClockRecordBean(String clock_time, String class_name, String student_name) {
        this.clock_time = clock_time;
        this.class_name = class_name;
        this.student_name = student_name;
    }

    public String getClock_time() {
        return clock_time;
    }

    public void setClock_time(String clock_time) {
        this.clock_time = clock_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
