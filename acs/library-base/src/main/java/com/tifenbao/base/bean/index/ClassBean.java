package com.tifenbao.base.bean.index;

import java.io.Serializable;
import java.util.List;

/**
 * 公示班级bean
 *
 * mar
 * 2019/8/2
 */
public class ClassBean implements Serializable {

        private String id;
        private String class_name;
        private List<RangBean> rang;

        private boolean isSclect=false;//是否当前所选点击班级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public List<RangBean> getRang() {
        return rang;
    }

    public void setRang(List<RangBean> rang) {
        this.rang = rang;
    }

    public void setSclect(boolean sclect) {
        isSclect = sclect;
    }

    public boolean isSclect() {
        return isSclect;
    }
}
