package com.tifenbao.base.http.classmode;

import com.tifenbao.base.bean.card.CheckInBean;
import com.tifenbao.base.bean.gallery.ImageListBean;
import com.tifenbao.base.bean.index.IndexBean;
import com.tifenbao.base.bean.search.SearchStudentBean;
import com.tifenbao.base.bean.signbook.ClockBean;
import com.tifenbao.base.bean.signbook.ClockDetailBean;
import com.tifenbao.base.network.BaseResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 班牌模式接口
 * <p>
 * Created by mar on 2018/3/14.
 */
public interface ClassApiService {

    @GET
    Observable<BaseResult<IndexBean>> getIndexClassBean(@Url String url);

    @GET
    Observable<BaseResult> setClass(@Url String url);

    @GET
    Observable<BaseResult<ImageListBean>> getImagData(@Url String url);

    @GET
    Observable<BaseResult<SearchStudentBean>> search(@Url String url);

    @GET
    Observable<BaseResult<CheckInBean>> checkIn(@Url String url);

    @GET
    Observable<BaseResult> addScore(@Url String url);

    @GET
    Observable<BaseResult<ClockDetailBean>> clockList(@Url String url);

    @GET
    Observable<BaseResult<String>> getSDKCode(@Url String url);

    @GET
    Observable<BaseResult<SearchStudentBean>> faceSearch(@Url String url);

    @GET
    Observable<BaseResult> faceCheckIn(@Url String url);

    @GET
    Observable<BaseResult<String>> attendanceCheckIn(@Url String url);

}
