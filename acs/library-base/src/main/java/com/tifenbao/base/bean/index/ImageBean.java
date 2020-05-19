package com.tifenbao.base.bean.index;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * 图片
 * mar
 * 2019/7/27
 */
public class ImageBean implements Serializable {

    private String image;//普通图片路径

    private String image_url;//校园图片路径

    private String photo_url;//照片模块参数名

    public ImageBean() {
    }

    public ImageBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }


    public String getUrl() {
        return TextUtils.isEmpty(getImage_url()) ? (TextUtils.isEmpty(getImage()) ? getPhoto_url() : getImage()) : getImage_url();
    }

}
