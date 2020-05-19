package com.tifenbao.base.bean.acs;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * mar
 * 2020/5/16
 */
public class TemperatureBean implements Serializable {

    private String mode;
    private float warning;
    private float base;
    private String limit;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public float getWarning() {
        return warning;
    }

    public void setWarning(float warning) {
        this.warning = warning;
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public TemperatureBean() {
    }
}
