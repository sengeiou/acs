package com.tifenbao.base.http.login;

import android.text.TextUtils;

import com.tifenbao.base.bean.upload.UploadBaseBean;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseParams;
import com.tifenbao.base.network.RetrofitClient;
import com.tifenbao.base.network.UrlPath;
import com.tifenbao.mvvmhabit.utils.RxUtils;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * mar
 * 2019/9/1
 */
public class LoginHttpDataUtils {

    /**
     * 登录
     */
    public static void getLoginData(String username, String password, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("useraccount", username);
        params.put("password", password);

        RetrofitClient.getInstance().create(LoginApiService.class).getLoginBean(BaseParams.get(UrlPath.LOGIN_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 设置状态
     */
    public static void setDeviceStatus(String school_id, String class_id, int mode, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);
        params.put("class_id", class_id);
        params.put("mode", String.valueOf(mode));

        RetrofitClient.getInstance().create(LoginApiService.class).setDeviceStatus(BaseParams.get(UrlPath.SET_DEVICE_STATUS_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 登录
     */
    public static void syncData(String school_id, String class_id, String classroom_id, int mode, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_id", school_id);
        params.put("class_id", class_id);
        params.put("classroom_id", classroom_id);
        params.put("mode", String.valueOf(mode));

        RetrofitClient.getInstance().create(LoginApiService.class).syncData(BaseParams.get(UrlPath.SYNC_DATA_URL, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    public static void sendImg(String cardId, File base64, UpLpadListener upLpadListener) {

        if (!base64.exists()) {
            ToastUtils.showShort("图片保存不成功");
            upLpadListener.onFailuer();
            return;
        }

        HttpUtils httpUtils = new HttpUtils();

        RequestParams params = new RequestParams();
        // 使用multipart表单上传文件

        params.addBodyParameter("id", cardId);
        params.addBodyParameter("bmstr", base64);
//			 params.addBodyParameter("bmstr",base64);


        httpUtils.send(HttpMethod.POST, UrlPath.ASYN_UPLOAD_IMG, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(int arg0, HttpException arg1, String arg2) {
                // TODO Auto-generated method stub
                upLpadListener.onFailuer();
            }

            @Override
            public void onSuccess(int arg0, ResponseInfo<String> arg1) {
                // TODO Auto-generated method stub
                upLpadListener.onSuccess(null);
            }
        });


    }

    public static void sendVoice(String id, File base64, UpLpadListener upLpadListener) {

        if (!base64.exists()) {
            ToastUtils.showShort("音频保存不成功");
            return;
        }

        HttpUtils httpUtils = new HttpUtils();

        RequestParams params = new RequestParams();
        // 使用multipart表单上传文件

        params.addBodyParameter("student_id", id);
        params.addBodyParameter("voice", base64);

        httpUtils.send(HttpMethod.POST, UrlPath.ASYN_UPLOAD_VOICE, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(int arg0, HttpException arg1, String arg2) {
                // TODO Auto-generated method stub
                upLpadListener.onFailuer();
            }

            @Override
            public void onSuccess(int arg0, ResponseInfo<String> arg1) {
                // TODO Auto-generated method stub


                if (arg1 != null && !TextUtils.isEmpty(arg1.result)) {
                    UploadBaseBean uploadBaseBean = new Gson().fromJson(arg1.result, UploadBaseBean.class);

                    if (uploadBaseBean != null && uploadBaseBean.data != null) {
                        upLpadListener.onSuccess(uploadBaseBean.data);
                    }

                } else {
                    upLpadListener.onFailuer();
                }

            }
        });


    }

    public interface UpLpadListener {

        void onFailuer();

        void onSuccess(UploadBaseBean.UploadBean uploadBean);

    }

}
