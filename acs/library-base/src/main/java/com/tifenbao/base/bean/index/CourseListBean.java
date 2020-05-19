package com.tifenbao.base.bean.index;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 一个时间段内的一周课表具体内容
 * mar
 * 2019/7/27
 */
public class CourseListBean  implements Serializable {

    @SerializedName("1")
    private CourseBean one;

    @SerializedName("2")
    private CourseBean two;

    @SerializedName("3")
    private CourseBean thread;

    @SerializedName("4")
    private CourseBean four;

    @SerializedName("5")
    private CourseBean five;

    @SerializedName("6")
    private CourseBean six;

    @SerializedName("7")
    private CourseBean seven;

    public CourseBean getOne() {
        return one;
    }

    public void setOne(CourseBean one) {
        this.one = one;
    }

    public CourseBean getTwo() {
        return two;
    }

    public void setTwo(CourseBean two) {
        this.two = two;
    }

    public CourseBean getThread() {
        return thread;
    }

    public void setThread(CourseBean thread) {
        this.thread = thread;
    }

    public CourseBean getFour() {
        return four;
    }

    public void setFour(CourseBean four) {
        this.four = four;
    }

    public CourseBean getFive() {
        return five;
    }

    public void setFive(CourseBean five) {
        this.five = five;
    }

    public CourseBean getSix() {
        return six;
    }

    public void setSix(CourseBean six) {
        this.six = six;
    }

    public CourseBean getSeven() {
        return seven;
    }

    public void setSeven(CourseBean seven) {
        this.seven = seven;
    }
}
