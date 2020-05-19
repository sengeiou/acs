package com.tifenbao.base.http.showschoolmode;

import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseParams;
import com.tifenbao.base.network.RetrofitClient;
import com.tifenbao.base.network.UrlPath;
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
public class ShowSchoolHttpDataUtils {


    /**
     * 获取欢迎模式初始化数据
     */
    public static void getIndexShowSchoolData(String school_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);

        RetrofitClient.getInstance().create(ShowSchoolApiService.class).getIndexShowSchoolBean(BaseParams.get(UrlPath.INDEX_SHOWSCHOOL_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
