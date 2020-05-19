package com.tifenbao.base.http.exammode;

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
public class ExamHttpDataUtils {


    /**
     * 获取考试模式初始化数据
     */
    public static void getIndexExamData(String class_id, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("class_id", class_id);

        RetrofitClient.getInstance().create(ExamApiService.class).getIndexExamBean(BaseParams.get(UrlPath.INDEX_EXAM_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
