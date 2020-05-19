package com.tifenbao.base.bean.gallery;

import com.tifenbao.base.bean.index.ImageBean;

import java.io.Serializable;
import java.util.List;

/**
 * mar
 * 2019/8/16
 */
public class ImageListBean implements Serializable {

    private List<ImageBean> data;

    public List<ImageBean> getData() {
        return data;
    }

    public void setData(List<ImageBean> data) {
        this.data = data;
    }
}
