package com.tifenbao.acs.mode;

/**
 * mar
 * 2020/5/13
 */
public class UrlPath {

    //    public static final String BASE_URL = "http://test.51xue.me";//测试环境接口URL：
    public static final String BASE_URL = "http://usys.51xue.me";//生产环境接口URL：
    public static final String BASE_IMGURL = "http://img.51xue.me/bbt/";//生产环境图片URL：

    public static final String ASYN_UPLOAD_IMG = BASE_URL + "/app_api/asyn_upload";//上传图片接口

    public static final String QUERY_SDK_CODE =BASE_URL + "/api/v1/face2/sdk_code/query";//APP查询设备序列号接口

    public static final String QUERY_PERSION =BASE_URL + "/api/v1/face2/persion/query";//APP查询人员列表接口

    public static final String ADD_RECORD =BASE_URL + "/api/v1/face2/record/add";//APP查询人员列表接口

    public static final String CHECK_VERSION =BASE_URL + "/api/v1/face2/app_version/query";//APP查询人员列表接口

}
