package com.tifenbao.base.bean.upload;

import java.io.Serializable;

/**
 * mar
 * 2019/8/29
 */
public class UploadBaseBean implements Serializable {

    public int code;
    public String msg;
    public UploadBean data;


    public static class UploadBean implements Serializable {


        private String voice;


        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getVoice() {
            return voice;
        }

    }

}
