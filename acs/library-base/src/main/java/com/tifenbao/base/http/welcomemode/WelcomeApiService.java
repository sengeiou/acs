package com.tifenbao.base.http.welcomemode;

import com.tifenbao.base.bean.index.IndexBean;
import com.tifenbao.base.network.BaseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 校牌模式接口
 * <p>
 * mar
 * 2019/9/1
 */
public interface WelcomeApiService {


    @GET
    Observable<BaseResult<IndexBean>> getIndexWelcomeBean(@Url String url);



}
