package com.tifenbao.base.bean;

public class MenuBean {

    public String mTitle;
    public int mSelectIcon;
    public int mNormalIcon;
    public boolean isSelect = false;//默认非选中状态
    public boolean isUnread = false;//是否存在未读小红点

    public MenuBean(int mSelectIcon, int mNormalIcon, String title) {
        this.mTitle = title;
        this.mSelectIcon = mSelectIcon;
        this.mNormalIcon = mNormalIcon;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean unread) {
        isUnread = unread;
    }
}
