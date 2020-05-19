package com.tifenbao.base.http.dormmode;

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
public interface DormApiService {


    @GET
    Observable<BaseResult<IndexBean>> getIndexDormBean(@Url String url);


    @GET
    Observable<BaseResult> setDorm(@Url String url);

    @GET
    Observable<BaseResult> chcekInDormFace(@Url String url);

    @GET
    Observable<BaseResult<CheckInBean>> chcekInDorm(@Url String url);

}
