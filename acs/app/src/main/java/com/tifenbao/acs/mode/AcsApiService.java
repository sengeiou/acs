package com.tifenbao.acs.mode;

import com.tifenbao.acs.bean.CodeBean;
import com.tifenbao.base.bean.acs.PersionListBean;
import com.tifenbao.base.network.BaseResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 接口
 * <p>
 * Created by mar on 2020/5/13.
 */
public interface AcsApiService {

    @GET
    Observable<BaseResult<CodeBean>> querySDKCode(@Url String url);

    @GET
    Observable<BaseResult<PersionListBean>> queryPersion(@Url String url);


}
