package com.tifenbao.acs.utils.updateversion;

import com.tifenbao.acs.BuildConfig;
import com.tifenbao.acs.mode.UrlPath;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseParams;
import com.tifenbao.base.network.RetrofitClient;
import com.tifenbao.mvvmhabit.utils.RxUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * mar
 * 2019/9/1
 */
public class UpdateHttpDataUtils {


    /**
     * 获取考试模式初始化数据
     */
    public static void getIndexExamData(String schoolNum,LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("tag", BaseConstant.BASE_UPDATE_TAB);
        params.put("code", String.valueOf(BuildConfig.VERSION_CODE));
        params.put("school_num",schoolNum);

        RetrofitClient.getInstance().create(UpdateApiService.class).getUpdateVersionBean(BaseParams.get(UrlPath.CHECK_VERSION, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
