package com.tifenbao.base.network;

public class UrlPath {

    //    public static final String BASE_URL = "http://test.51xue.me";//测试环境接口URL：
    public static final String BASE_URL = "http://bbt.51xue.me";//生产环境接口URL：
    public static final String BASE_IMGURL = "http://img.51xue.me/bbt/";//生产环境图片URL：

    public static final String ASYN_UPLOAD_IMG = BASE_URL + "/app_api/asyn_upload";//上传图片接口

    public static final String ASYN_UPLOAD_VOICE = BASE_URL + "/app_api/upload_voice_mp3";//上传图片接口

    public static final String SYNC_DATA_URL = BASE_URL + "/app_api/sync_data";//轮询接口

    public static final String LATEST_VERSION_URL = BASE_URL + "/app_api/latest_version";//版本升级接口

    //---------------登录接口
    public static final String LOGIN_URL = "/app_api/login";//登录

    //---------------班牌模式接口
    public static final String INDEX_CLASS_DATA_URL = "/app_api/index_data";//初始化

    public static final String SET_CLASS_URL = "/app_api/set_class";//设置班级

    public static final String GET_GALLERY_URL = "/app_api/gallery";//获取相册数据

    public static final String SEARCH_URL = "/app_api/search";//打卡搜索

    public static final String CHECKIN_URL = "/app_api/checkIn";//全局打卡考勤

    public static final String ADD_SCORE_URL = "/app_api/add_score";//班级评分

    public static final String CLOCK_DETAIL_URL = "/app_api/clock_detail";//考勤统计

    public static final String FACE_SEARCH_URL = "/app_api/search_face";//人脸查询信息

    public static final String CHECKIN_ATTENDANCE_URL = "/app_api/attendance";//打卡考勤

    public static final String FACE_CHECKIN_URL = "/app_api/checkIn_face_batch";//人脸考勤


    //---------------校牌模式接口
    public static final String INDEX_SCHOOL_DATA_URL = "/app_api/school_data";//初始化

    public static final String SET_SCHOOL_URL = "/app_api/set_school";//设置学校

    public static final String CHECKIN_SCHOOL_FACE_URL = "/app_api/checkIn_school_face_batch";//学校模式人脸识别

    public static final String CHECKIN_SCHOOL_URL = "/app_api/checkIn_school";//学校模式打卡识别

    //---------------宿舍模式接口
    public static final String INDEX_DORIMITORY_DATA_URL = "/app_api/dormitory_data";//初始化

    public static final String SET_DORIMITORY_URL = "/app_api/set_dormitory";//设置宿舍

    public static final String CHECKIN_DORIMITORY_FACE_URL  = "/app_api/checkIn_dormitory_face_batch";//宿舍模式人脸识别


    public static final String CHECKIN_DORIMITORY_URL = "/app_api/checkIn_dormitory";//学校模式打卡识别

    //---------------宿舍模式接口
    public static final String INDEX_WELCOME_DATA_URL = "/app_api/welcome_data";//初始化

    //---------------考试模式接口
    public static final String INDEX_EXAM_DATA_URL = "/app_api/exam_data";//初始化

    //---------------校园展示模式接口
    public static final String INDEX_SHOWSCHOOL_DATA_URL = "/app_api/new_school_data";//初始化

    //-----------------人脸识别相关接口
    public static final String GET_CONFIGFILE_URL = "http://bbt.51xue.me/device_config/device_config_aibao_10inc.json";//获取人脸sdk配置文件

    public static final String GET_SDK_CODE_URL = "/app_api/get_sdk_code";//获取人脸sdk授权码

    public static final String SET_DEVICE_STATUS_URL = "/app_api/device_status";//更新后台mode状态

    public static final String GET_IMEI_DATA = "/app_api/get_imei_data";//更新后台mode状态

}
