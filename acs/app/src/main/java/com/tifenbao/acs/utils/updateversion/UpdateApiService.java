package com.tifenbao.acs.utils.updateversion;

import com.tifenbao.base.bean.version.VersionBean;
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
public interface UpdateApiService {


    @GET
    Observable<BaseResult<VersionBean>> getUpdateVersionBean(@Url String url);



}
