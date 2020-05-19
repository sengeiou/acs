package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 学校/班级通知
 * mar
 * 2019/7/27
 */
public class MessageBean implements Serializable {

    private String id;
    private String message_title;
    private String message_content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
}
