package com.tifenbao.base.bean.rxbus;

import java.io.Serializable;

/**
 * 用于更新人脸识别状态
 * <p>
 * mar
 * 2019/8/25
 */
public class CardFaceSwitchBean implements Serializable {

    public boolean isAuto = false;
    public boolean isOpen = false;

    public CardFaceSwitchBean(boolean isOpen, boolean isAuto) {
        this.isOpen = isOpen;
        this.isAuto = isAuto;
    }

}
