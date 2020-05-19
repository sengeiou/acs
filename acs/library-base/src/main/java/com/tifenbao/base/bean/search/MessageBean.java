package com.tifenbao.base.bean.search;

/**
 * 留言
 * <p>
 * mar
 * 2019/8/17
 */
public class MessageBean extends BaseStudentMsgBean {

    private String message;
    private String voice;//为空时文字，有链接时录音
    private String send_type;//2家人，1自己
    private String isread;
    private String create_date;

    public MessageBean() {
    }

    public MessageBean(String voice, String send_type) {
        this.voice = voice;
        this.send_type = send_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
