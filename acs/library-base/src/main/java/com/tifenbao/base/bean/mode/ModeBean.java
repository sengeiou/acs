package com.tifenbao.base.bean.mode;

import java.io.Serializable;

/**
 * mar
 * 2019/8/27
 */
public class ModeBean implements Serializable {

    private int moodType;
    private String name;
    private int drawableId;


    public ModeBean(String name, int moodType, int drawableId) {
        this.name = name;
        this.moodType = moodType;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getMoodType() {
        return moodType;
    }

    public void setMoodType(int moodType) {
        this.moodType = moodType;
    }
}
