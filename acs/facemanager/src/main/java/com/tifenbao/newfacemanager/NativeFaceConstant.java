package com.tifenbao.newfacemanager;

public class NativeFaceConstant {

    public static final int AUTOCLOSE=1000*10;//10秒自动停止

    public static final int STYLE_1=1001;//样式1
    public static final int STYLE_2=1002;//样式1
    public static final int STYLE_3=1003;//样式1
	
	public static final int SUCCESS=0;//写入成功
	public static final int ERROR=1;//写入失败
	public static final int UNNETWORK=2;//下载失败

    public static final int CAMERA_SEARCH_ERROR=3;//搜索人脸失败
    public static final int CAMERA_SEARCH_SUCCESS=4;//搜索人脸成功


    public static final int SUCCESS_FILE=5;//获取配置文件成功
    public static final int ERROR_FILE=6;//获取配置文件失败


    public static final int ALL_DELETE_SUCCESS=10;//全部删除

    public static final int FACE_NONE=1;//无活体
    public static final int FACE_VIS_LIVENESS=2;//单目
    public static final int FACE_NIR_LIVENESS_PHONE=3;//双目防手机
    public static final int FACE_NIR_LIVENESS=4;//双目
    public static final int FACE_DEPTH_LIVENESS=5;//结构光

}
