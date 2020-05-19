package com.tifenbao.acs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * mar
 * 2020/5/16
 */
public class CodeBean implements Parcelable {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
    }

    public CodeBean() {
    }

    protected CodeBean(Parcel in) {
        this.code = in.readString();
    }

    public static final Parcelable.Creator<CodeBean> CREATOR = new Parcelable.Creator<CodeBean>() {
        @Override
        public CodeBean createFromParcel(Parcel source) {
            return new CodeBean(source);
        }

        @Override
        public CodeBean[] newArray(int size) {
            return new CodeBean[size];
        }
    };
}
