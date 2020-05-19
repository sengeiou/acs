package com.tifenbao.base.bean.rxbus;

/**
 * mar
 * 2019/8/26
 */
public class BaseRxBusBean {

    public String type;
    public Object data;


    public BaseRxBusBean() {
    }

    public BaseRxBusBean(String type, Object data) {
        this.type = type;
        this.data = data;
    }

}
