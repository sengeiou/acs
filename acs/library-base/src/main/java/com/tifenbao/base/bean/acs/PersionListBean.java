package com.tifenbao.base.bean.acs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * mar
 * 2020/5/16
 */
public class PersionListBean implements Serializable {

    private List<PersionBean> list;
    private TemperatureBean temperature;

    public List<PersionBean> getList() {
        return list;
    }

    public void setList(List<PersionBean> list) {
        this.list = list;
    }

    public TemperatureBean getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureBean temperature) {
        this.temperature = temperature;
    }

    public PersionListBean() {
    }

}
