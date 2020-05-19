package com.tifenbao.base.http.login;

import com.tifenbao.base.bean.HttpModeBean;
import com.tifenbao.base.bean.index.IndexBean;
import com.tifenbao.base.bean.login.LoginBean;
import com.tifenbao.base.network.BaseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 登录模式接口
 * <p>
 * mar
 * 2019/9/1
 */
public interface LoginApiService {

    @GET
    Observable<BaseResult<LoginBean>> getLoginBean(@Url String url);

    @GET
    Observable<BaseResult> setDeviceStatus(@Url String url);

    @GET
    Observable<BaseResult<IndexBean>> syncData(@Url String url);

    @GET
    Observable<BaseResult<HttpModeBean>> getHttpMode(@Url String url);
}
