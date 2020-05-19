package com.tifenbao.base.http.classmode;

import com.google.gson.Gson;
import com.tifenbao.base.bean.CheckUpBean;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseParams;
import com.tifenbao.base.network.RetrofitClient;
import com.tifenbao.base.network.UrlPath;
import com.tifenbao.mvvmhabit.utils.RxUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 调用各种接口方法类
 * <p>
 * mar
 * 2019/8/14
 */
public class ClassHttpDataUtils {


    /**
     * 获取班牌模式初始化数据
     */
    public static void getIndexClassData(String class_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);

        RetrofitClient.getInstance().create(ClassApiService.class).getIndexClassBean(BaseParams.get(UrlPath.INDEX_CLASS_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 设置班级数据
     */
    public static void setClassData(String class_id, String classroom_id, String uid, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        params.put("classroom_id", classroom_id);
        params.put("uid", uid);

        RetrofitClient.getInstance().create(ClassApiService.class).setClass(BaseParams.get(UrlPath.SET_CLASS_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 获取相册数据
     */
    public static void getImageData(String img_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("id", img_id);

        RetrofitClient.getInstance().create(ClassApiService.class).getImagData(BaseParams.get(UrlPath.GET_GALLERY_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 普通刷卡
     */
    public static void checkInData(String class_id, String cardNo, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        params.put("cardNo", cardNo);

        RetrofitClient.getInstance().create(ClassApiService.class).checkIn(BaseParams.get(UrlPath.CHECKIN_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 值日生刷卡评分
     */
    public static void addScore(String class_id, String cardNo, String score, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        params.put("cardNo", cardNo);
        params.put("score", score);

        RetrofitClient.getInstance().create(ClassApiService.class).addScore(BaseParams.get(UrlPath.ADD_SCORE_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 刷卡查询
     */
    public static void searchData(String class_id, String cardNo, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        params.put("cardNo", cardNo);

        RetrofitClient.getInstance().create(ClassApiService.class).search(BaseParams.get(UrlPath.SEARCH_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 人脸查询
     */
    public static void faceSearch(String class_id, String student_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        params.put("student_id", student_id);

        RetrofitClient.getInstance().create(ClassApiService.class).faceSearch(BaseParams.get(UrlPath.FACE_SEARCH_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 打卡考勤
     */
    public static void attendancecheckIn(String cardNo, String status, String type, float temperature, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("cardNo", cardNo);
        params.put("status", status);
        params.put("type", type);
        params.put("time", String.valueOf(System.currentTimeMillis()));
        params.put("temperature", String.valueOf(temperature));

        RetrofitClient.getInstance().create(ClassApiService.class).attendanceCheckIn(BaseParams.get(UrlPath.CHECKIN_ATTENDANCE_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 人脸考勤
     */
    public static void faceCheckIn(String class_id, List<CheckUpBean> checkUpBeanList, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);
        Gson gson = new Gson();
        String jsonString = gson.toJson(checkUpBeanList);
        params.put("json", jsonString);
        RetrofitClient.getInstance().create(ClassApiService.class).faceCheckIn(BaseParams.get(UrlPath.FACE_CHECKIN_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 考勤统计
     */
    public static void clockListData(String class_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);

        RetrofitClient.getInstance().create(ClassApiService.class).clockList(BaseParams.get(UrlPath.CLOCK_DETAIL_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 获取授权码
     */
    public static void getSDKCode(String school_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);

        RetrofitClient.getInstance().create(ClassApiService.class).getSDKCode(BaseParams.get(UrlPath.GET_SDK_CODE_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
