package com.tifenbao.base.http.schoolmode;

import com.tifenbao.base.bean.card.CheckInBean;
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
public interface SchoolApiService {


    @GET
    Observable<BaseResult<IndexBean>> getIndexSchoolBean(@Url String url);


    @GET
    Observable<BaseResult> setSchool(@Url String url);

    @GET
    Observable<BaseResult> chcekInSchoolFace(@Url String url);

    @GET
    Observable<BaseResult<CheckInBean>> chcekInSchool(@Url String url);

}
