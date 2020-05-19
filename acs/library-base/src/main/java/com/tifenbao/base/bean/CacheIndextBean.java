package com.tifenbao.base.bean;

import com.tifenbao.base.bean.index.IndexBean;

import java.io.Serializable;

/**
 * 缓存初始化数据
 * <p>
 * mar
 * 2019/7/28
 */
public class CacheIndextBean implements Serializable {

    private static CacheIndextBean cacheIndextBean;
    private IndexBean indexBean;

    //自行实例化，提供全局的访问方法
    public static CacheIndextBean getInstance() {
        if (cacheIndextBean == null)
            cacheIndextBean = new CacheIndextBean();
        return cacheIndextBean;
    }

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexClassBean) {
        this.indexBean = indexClassBean;
    }


}
