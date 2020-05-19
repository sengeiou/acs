package com.tifenbao.base.http.schoolmode;

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
 * mar
 * 2019/9/1
 */
public class SchoolHttpDataUtils {


    /**
     * 获取校牌模式初始化数据
     */
    public static void getIndexSchoolData(String school_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);

        RetrofitClient.getInstance().create(SchoolApiService.class).getIndexSchoolBean(BaseParams.get(UrlPath.INDEX_SCHOOL_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 设置学校编号数据
     */
    public static void setSchoolData(String school_id, String uid, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);
        params.put("uid", uid);

        RetrofitClient.getInstance().create(SchoolApiService.class).setSchool(BaseParams.get(UrlPath.SET_SCHOOL_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 人脸识别打卡
     */
    public static void checkInSchoolFace(String school_id, List<CheckUpBean> checkUpBeanList, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);
        Gson gson = new Gson();
        String jsonString = gson.toJson(checkUpBeanList);
        params.put("json", jsonString);

        RetrofitClient.getInstance().create(SchoolApiService.class).chcekInSchoolFace(BaseParams.get(UrlPath.CHECKIN_SCHOOL_FACE_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 打卡识别
     */
    public static void checkInSchool(String school_id, String cardNo, float temperature, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);
        params.put("cardNo", cardNo);
        params.put("temperature", String.valueOf(temperature));

        RetrofitClient.getInstance().create(SchoolApiService.class).chcekInSchool(BaseParams.get(UrlPath.CHECKIN_SCHOOL_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
