package com.tifenbao.base.http.dormmode;

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
public class DormHttpDataUtils {


    /**
     * 获取校牌模式初始化数据
     */
    public static void getIndexDormData(String dormitory_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("dormitory_id", dormitory_id);

        RetrofitClient.getInstance().create(DormApiService.class).getIndexDormBean(BaseParams.get(UrlPath.INDEX_DORIMITORY_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 设置学校编号数据
     */
    public static void setDormData(String dormitory_id, String uid, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("dormitory_id", dormitory_id);
        params.put("uid", uid);

        RetrofitClient.getInstance().create(DormApiService.class).setDorm(BaseParams.get(UrlPath.SET_DORIMITORY_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 人脸识别打卡
     */
    public static void checkInDormFace(String dormitory_id, List<CheckUpBean> checkUpBeanList, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("dormitory_id", dormitory_id);
        Gson gson = new Gson();
        String jsonString = gson.toJson(checkUpBeanList);
        params.put("json", jsonString);

        RetrofitClient.getInstance().create(DormApiService.class).chcekInDormFace(BaseParams.get(UrlPath.CHECKIN_DORIMITORY_FACE_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 打卡识别
     */
    public static void checkInDorm(String dormitory_id, String cardNo, float temperature, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("dormitory_id", dormitory_id);
        params.put("cardNo", cardNo);
        params.put("temperature", String.valueOf(temperature));

        RetrofitClient.getInstance().create(DormApiService.class).chcekInDorm(BaseParams.get(UrlPath.CHECKIN_DORIMITORY_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
