package com.tifenbao.base.bean.acs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * mar
 * 2020/5/16
 */
public class PersionBean implements Serializable {

    private String guid;
    private String num;
    private String name;
    private String photo;
    private long update_time;
    private String isdel;
    private String user_type;

    private float temperature;//温度
    private boolean isRedPoint=false;

    private int code = -1;//-1从未尝试过，0	public static final int SUCCESS=0;//写入成功，ERROR=1;//写入失败，UNNETWORK=2;//下载失败
    private long faceId;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public boolean isRedPoint() {
        return isRedPoint;
    }

    public void setRedPoint(boolean redPoint) {
        isRedPoint = redPoint;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getFaceId() {
        return faceId;
    }

    public void setFaceId(long faceId) {
        this.faceId = faceId;
    }

    public PersionBean() {
    }

}
