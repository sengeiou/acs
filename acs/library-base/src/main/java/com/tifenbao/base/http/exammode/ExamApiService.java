package com.tifenbao.base.http.exammode;

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
public interface ExamApiService {


    @GET
    Observable<BaseResult<IndexBean>> getIndexExamBean(@Url String url);



}
