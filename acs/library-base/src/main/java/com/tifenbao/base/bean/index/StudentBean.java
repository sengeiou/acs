package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 学生列表
 * mar
 * 2019/7/28
 */
public class StudentBean implements Serializable {

    private String id;
    private String student_name;
    private String card_number;
    private String photo;
    private long update_time;
    private String class_name;

    private float temperature;//温度
    private boolean isRedPoint=false;

    private int code = -1;//-1从未尝试过，0	public static final int SUCCESS=0;//写入成功，ERROR=1;//写入失败，UNNETWORK=2;//下载失败
    private long faceId;
    private int status = 10;//未到

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public long getFaceId() {
        return faceId;
    }

    public void setFaceId(long faceId) {
        this.faceId = faceId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public boolean isRedPoint() {
        return isRedPoint;
    }

    public void setRedPoint(boolean redPoint) {
        isRedPoint = redPoint;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
