package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 校园文化图文
 * mar
 * 2019/7/27
 */
public class SchoolArticleBean implements Serializable {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
