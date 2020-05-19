package com.tifenbao.newfacemanager;

public interface FaceMangerCallback {

    public void setDeviceID(int code,String msg, String deviceId);

    public void ErrorCode(String msg);

    public void successCode(String msg);


    public void DownLoadCallbackCode(int code, String filename, String id, long faceid);

    public void DeleteCallbackCode(int code, String id);

    public void cameraCallback(int code, int sort, String id);

    public void closecameraCallback();

    public void DownLoadConfigFileCallback(int code);

    public void initConfigFile(int code, String deviceMsg);
}
