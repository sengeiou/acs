package com.tifenbao.acs.mode;

import android.provider.Settings;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tifenbao.base.BuildConfig;
import com.tifenbao.base.bean.acs.PersionBean;
import com.tifenbao.base.bean.upload.UploadBaseBean;
import com.tifenbao.base.http.login.LoginHttpDataUtils;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseParams;
import com.tifenbao.base.network.RetrofitClient;
import com.tifenbao.mvvmhabit.base.BaseApplication;
import com.tifenbao.mvvmhabit.utils.RxUtils;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 调用各种接口方法类
 * <p>
 * mar
 * 2019/8/14
 */
public class AcsHttpDataUtils {


    /**
     * 获取初始化数据
     */
    public static void querySDKCode(String school_num, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_num", school_num);

        RetrofitClient.getInstance().create(AcsApiService.class).querySDKCode(BaseParams.get(UrlPath.QUERY_SDK_CODE, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }


    /**
     * 查询人员列表
     */
    public static void queryPersion(String school_num, String update_time, LifecycleProvider lifecycleProvider, BaseObserver baseObserver) {

        Map<String, String> params = new HashMap<>();

        params.put("school_num", school_num);
        params.put("update_time", update_time);

        RetrofitClient.getInstance().create(AcsApiService.class).queryPersion(BaseParams.get(UrlPath.QUERY_PERSION, params))
                .compose(RxUtils.bindToLifecycle(lifecycleProvider))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 识别上传
     */
    public static void saveRecord(String school_num, PersionBean persionBean, String fileName,String clock_time, UpLpadListener upLpadListener) {

        File base64=new File(fileName);
        if (!base64.exists()) {
            ToastUtils.showShort("图片保存不成功");
            upLpadListener.onFailuer();
            return;
        }

        HttpUtils httpUtils = new HttpUtils();

        RequestParams params = new RequestParams();
        // 使用multipart表单上传文件

        params.addBodyParameter("school_num", school_num);
        params.addBodyParameter("source", "lanbi");
        params.addBodyParameter("guid", persionBean.getGuid());
        params.addBodyParameter("user_type", persionBean.getUser_type());
        params.addBodyParameter("name", persionBean.getName());
        params.addBodyParameter("num", persionBean.getNum());
        params.addBodyParameter("clock_time",clock_time);
        params.addBodyParameter("temperature",String.valueOf(persionBean.getTemperature()));
        String time = String.valueOf(System.currentTimeMillis());
        params.addBodyParameter("token", BaseParams.getMd5Value("tfb2020" + time));
        params.addBodyParameter("timeline", time);
        params.addBodyParameter("imei", Settings.Secure.getString(BaseApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));
        params.addBodyParameter("version", BuildConfig.VERSION_NAME);
        params.addBodyParameter("img", base64);
//			 params.addBodyParameter("bmstr",base64);
        httpUtils.send(HttpRequest.HttpMethod.POST,UrlPath.ADD_RECORD, params, new RequestCallBack<String>() {

            @Override
            public void onFailure(int arg0, HttpException arg1, String arg2) {
                // TODO Auto-generated method stub
                upLpadListener.onFailuer();
            }

            @Override
            public void onSuccess(int arg0, ResponseInfo<String> arg1) {
                // TODO Auto-generated method stub
                try{
                    JSONObject jsonObject=new JSONObject(arg1.result);
                    upLpadListener.onSuccess(jsonObject.getString("code"));
                }catch (Exception e){

                }

            }
        });


    }

    public interface UpLpadListener {

        void onFailuer();

        void onSuccess(String code);

    }

}
