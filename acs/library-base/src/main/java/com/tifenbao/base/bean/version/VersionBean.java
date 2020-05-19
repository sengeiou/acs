package com.tifenbao.base.bean.version;

import java.io.Serializable;

/**
 * 版本升级bean类
 * <p>
 * mar
 * 2019/9/15
 */
public class VersionBean implements Serializable {

    private String title;
    private String content;
    private String version;
    private int version_code;
    private String download_url;


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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
