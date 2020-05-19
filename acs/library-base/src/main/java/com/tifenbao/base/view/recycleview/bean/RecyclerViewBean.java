package com.tifenbao.base.view.recycleview.bean;

import java.io.Serializable;

/**
 * Created by luojg on 2017/6/16.
 */

public class RecyclerViewBean implements Serializable {
    public static final int TYPE_CATEGORY_SUB_PIC = 1;//图片
    public static int TYPE_CATEGORY_SUB_TILE = 2;//标题
    public static int TYPE_CATEGORY_SUB_TWO_EMPTY = 3;//空
    public static int TYPE_CATEGORY_SUB_PHOTOS = 4;//拍照
    public static final int TYPE_CATEGORY_SUB_VIDEO = 5;//视频
    public static final int TYPE_LOCATION_LABEL = 6;//地点标签
    public static final int TYPE_HEIGHT_WEIGHT = 7;//身高体重
    public static final int TYPE_CONTENT = 8;//内容
    public static final int TYPE_HOME_SERVER_EIGHT = 9;//首页十宫格接口数据类型
    public static final int TYPE_HOME_LOCAL_EIGHT = 33;//首页十宫格更多工具类型
    public static final int TYPE_MINE_FOUR = 11 ;//个人中心四宫格类型
    private String id;
    private int type;
    private int span;
    private Object data;
    private int totlImage;//每个日期的图片个数
    private boolean isSelectAll = false;

    private int index_list;//数组序号


    private boolean isMasking = false;//是否需要蒙版

    private String isReport;//"no"代表没有上报过。"already"代表已经上报过

    public String getIsReport() {
        return isReport;
    }

    public void setIsReport(String isReport) {
        this.isReport = isReport;
    }

    public boolean isMasking() {
        return isMasking;
    }

    public void setMasking(boolean masking) {
        isMasking = masking;
    }

    public int getTotlImage() {
        return totlImage;
    }

    public void setTotlImage(int totlImage) {
        this.totlImage = totlImage;
    }

    public boolean isSelectAll() {
        return isSelectAll;
    }

    public void setSelectAll(boolean selectAll) {
        isSelectAll = selectAll;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public int getIndex_list() {
        return index_list;
    }

    public void setIndex_list(int index_list) {
        this.index_list = index_list;
    }
}
