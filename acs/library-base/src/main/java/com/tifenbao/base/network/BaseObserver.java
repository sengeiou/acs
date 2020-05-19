package com.tifenbao.base.network;

import android.net.ParseException;
import android.text.TextUtils;

import com.tifenbao.mvvmhabit.http.ResponseThrowable;
import com.tifenbao.mvvmhabit.utils.KLog;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by rongqiang on 2019/4/30 15:06
 */
public abstract class BaseObserver<T extends BaseResult> implements Observer<T> {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVICE_UNAVAILABLE = 503;

    @Override
    public void onNext(T response) {

        if ("0".equals(response.code)) {
            //成功
            onSuccess(response);
        } else {
            //失败

            onFailing(response);
        }

        onComplete();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "操作未授权";
                    break;
                case FORBIDDEN:
                    ex.message = "请求被拒绝";
                    break;
                case NOT_FOUND:
                    ex.message = "资源不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "服务器执行超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器内部错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器不可用";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }

        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";

        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";

        } else if (e instanceof javax.net.ssl.SSLException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";

        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";

        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";

        } else if (e instanceof java.net.UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "主机地址未知";

        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";

        }
        onComplete();
        KLog.e(ex.toString());
        ToastUtils.showShort(ex.message);
    }

    /**
     * 约定异常 这个具体规则需要与服务端或者领导商讨定义
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

    public abstract void onSuccess(T response);

    public void onFailing(T response) {
        String message = response.message;
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort("请求错误");

        } else {
            ToastUtils.showShort(message);
        }

    }


}
