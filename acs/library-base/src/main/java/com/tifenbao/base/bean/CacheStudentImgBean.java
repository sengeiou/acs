package com.tifenbao.base.bean;

import com.tifenbao.base.bean.acs.PersionBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 缓存人脸图片数据
 * <p>
 * mar
 * 2019/7/28
 */
public class CacheStudentImgBean implements Serializable {

    private List<PersionBean> studentBeanList = new ArrayList<>();

    public List<PersionBean> getStudentBeanList() {
        return studentBeanList;
    }

    public void setStudentBeanList(List<PersionBean> studentBeanList) {
        this.studentBeanList.clear();
        this.studentBeanList.addAll(studentBeanList);
    }
}
