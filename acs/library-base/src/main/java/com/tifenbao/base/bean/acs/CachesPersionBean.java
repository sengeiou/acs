package com.tifenbao.base.bean.acs;

import java.io.Serializable;

/**
 * 用于缓存上传接口
 *
 * mar
 * 2020/5/19
 */
public class CachesPersionBean implements Serializable {

    private String school_num;
    private PersionBean persionBean;
    private String fileName;
    private int upload_num;//上传过多少次
    private String clock_time;


    public String getSchool_num() {
        return school_num;
    }

    public void setSchool_num(String school_num) {
        this.school_num = school_num;
    }

    public void setClock_time(String clock_time) {
        this.clock_time = clock_time;
    }

    public String getClock_time() {
        return clock_time;
    }

    public PersionBean getPersionBean() {
        return persionBean;
    }

    public void setPersionBean(PersionBean persionBean) {
        this.persionBean = persionBean;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getUpload_num() {
        return upload_num;
    }

    public void setUpload_num(int upload_num) {
        this.upload_num = upload_num;
    }
}
