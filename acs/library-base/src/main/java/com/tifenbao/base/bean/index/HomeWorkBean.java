package com.tifenbao.base.bean.index;

import java.io.Serializable;
import java.util.List;

/**
 * 作业
 *
 * mar
 * 2019/7/27
 */
public class HomeWorkBean implements Serializable {

    private String id;
    private String title;
    private String subject;
    private String content;
    private List<ImageBean> image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImageBean> getImage() {
        return image;
    }

    public void setImage(List<ImageBean> image) {
        this.image = image;
    }
}
