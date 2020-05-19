package com.tifenbao.base.base;

import android.content.Context;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.tifenbao.base.bean.acs.PersionBean;
import com.tifenbao.base.bean.acs.PersionListBean;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.network.UrlPath;
import com.tifenbao.base.util.CacheFaceUtils;
import com.tifenbao.base.util.CacheUtils;
import com.tifenbao.base.view.dialog.view.LoadingDialog;
import com.tifenbao.base.view.dialog.view.LoginDialog;
import com.tifenbao.mvvmhabit.base.BaseActivity;
import com.tifenbao.mvvmhabit.base.BaseViewModel;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.tifenbao.newfacemanager.FaceManagerUtils;
import com.tifenbao.newfacemanager.FaceMangerCallback;
import com.tifenbao.newfacemanager.NativeFaceConstant;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android_serialport_api.SerialPortListener;
import android_serialport_api.SerialPortUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * mar
 * 2019/7/28
 */
public abstract class BaseLandscapeActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseActivity<V, VM> {

    protected Disposable syncDataDisposable;//轮询查询

    protected Context mContext;

    private StringBuilder cardNo;//卡号
    private long keyEndTime;//记录打卡时间

    protected CacheUtils cacheUtils;//缓存

    protected LoginDialog dialog;

    protected LoadingDialog loadingDialog;//菊花

    protected FaceManagerUtils faceManagerUtils;

    protected boolean isDownLoadImg = false;

    protected List<PersionBean> persionBeanList;

    protected NetWorkStateReceiver netBroadcastReceiver;

    protected  SerialPortUtils serialPortUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mContext = this;
        cardNo = new StringBuilder();
        dialog = LoginDialog.getInstance();
        loadingDialog = LoadingDialog.getInstance();
        cacheUtils = new CacheUtils();

        EventBus.getDefault().register(this);

        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //实例化IntentFilter对象
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            netBroadcastReceiver = new NetWorkStateReceiver();
            //注册广播接收
            registerReceiver(netBroadcastReceiver, filter);
        }

        syncData();//每隔十分钟轮询一次
//        if (!BaseConstant.IS_CHECK_UPDATE) {//版本升级
//            BaseConstant.IS_CHECK_UPDATE = true;
//            VersionUpdate versionUpdate = new VersionUpdate();
//            versionUpdate.updateVersions(viewModel.getLifecycleProvider(), false);
//        }

//        initSerialport();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && loadingDialog != null) {
            loadingDialog.cancel();
        }

        if (event.getEventTime() - keyEndTime > 200) {
            cardNo.setLength(0);
        }

        keyEndTime = event.getEventTime();

        String code = KeyEvent.keyCodeToString(keyCode);

        if (!TextUtils.isEmpty(code)) {
            char[] codes = code.toCharArray();
            code = "";
            for (int i = 0; i < codes.length; i++) {
                if (("0123456789").indexOf(codes[i] + "") != -1) {
                    code += codes[i];
                }
            }

            if (TextUtils.isEmpty(code)) {

                if (!TextUtils.isEmpty(cardNo.toString())) {
                    searchCard(cardNo.toString());
                    cardNo.setLength(0);
                }

            } else {
                cardNo.append(code);
            }

        }


        return false;
    }

    /**
     * 查询卡号信息
     */
    protected abstract void setFaceError();

    /**
     * 查询卡号信息
     */
    protected abstract void setFaceSuccess();

    /**
     * 查询卡号信息
     */
    protected abstract void searchCard(String cardNo);

    /**
     * 更新数据
     */
    protected abstract void updateData();

    protected  abstract void updateImg();

    /**
     * 网络状态变更
     */
    protected abstract void onPhoneNetworkChange(int type);


    /**
     * 串口调试
     */
    private void initSerialport() {

        serialPortUtils = new SerialPortUtils();
        serialPortUtils.startSerialPort(this, "/dev/ttyS0", "115200", new SerialPortListener() {
            @Override
            public void CallbackCode(String code) {
                searchCard(code);
            }
        });
    }


    /**
     * 不想改了，将就着先用着吧
     */
    protected void initFaceManager() {

        faceManagerUtils = new FaceManagerUtils();

        faceManagerUtils.init(this, new FaceMangerCallback() {
            @Override
            public void setDeviceID(int code,String msg, String deviceId) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ErrorDeviceID", msg + "----验证码错误");
                        if(code==0){
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    faceManagerUtils.Auth(CacheUtils.get(mContext).getAsString(CacheUtils.SDK_CODE));
                                }
                            },1000);

                        }else{
                            ToastUtils.showShort(msg);
                        }

                    }
                });

            }

            @Override
            public void ErrorCode(String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(msg);
                    }
                });
            }

            @Override
            public void successCode(String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        faceManagerUtils.getConfigFile();
                    }
                });
            }

            @Override
            public void DownLoadCallbackCode(int code, String filename, String id, long faceid) {
                setFaceImage(code, filename, id, faceid);
            }

            @Override
            public void DeleteCallbackCode(int code, String id) {
                if (code == NativeFaceConstant.ALL_DELETE_SUCCESS) {
//                    setFaceImage(-1, "", "", -1);
                }
            }

            @Override
            public void cameraCallback(int code, int sort, String id) {

            }

            @Override
            public void closecameraCallback() {

            }

            @Override
            public void DownLoadConfigFileCallback(int code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (code == NativeFaceConstant.SUCCESS) {
                            ToastUtils.showShort("配置文件下载成功");
                            FaceCameraInit();
                        } else {
                            ToastUtils.showShort("配置文件下载失败");
                        }
                    }
                });
            }

            @Override
            public void initConfigFile(int code, String deviceMsg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (code == NativeFaceConstant.SUCCESS_FILE) {//配置文件成功
                            FaceCameraInit();
                        } else {//配置文件失败
                            ToastUtils.showShort("配置文件失败 进行下载");
                            faceManagerUtils.initConfigFile(UrlPath.GET_CONFIGFILE_URL);
                        }
                    }
                });
            }
        });

    }

    protected void FaceCameraInit() {

        BaseConstant.IS_FACE_COMMIT = true;
        setFaceSuccess();

        if (!isDownLoadImg) {
            if (cacheUtils.getAsObject(CacheUtils.IMAGE_FACE_URL) == null) {//判断是否有缓存数据
                faceManagerUtils.deleteAllFace();
                return;
            }
//            setFaceImage(-1, "", "", -1);
        }

    }

    protected synchronized void setFaceImage(final int code, final String filename, final String id, final long faceid) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(persionBeanList==null){
                    return;
                }


                PersionBean persionBean = CacheFaceUtils.getInstance().setCacheFaceImage(code, persionBeanList, filename, id, faceid);

                if (persionBean != null) {
                    isDownLoadImg = true;
//                    UrlPath.BASE_IMGURL +
                    faceManagerUtils.AddFace(persionBean.getPhoto(), persionBean.getName(), persionBean.getGuid());
                } else {
                    isDownLoadImg = false;
                    updateImg();
//                    int success = CacheFaceUtils.getInstance().getSuccessFace();
//                    int error = CacheFaceUtils.getInstance().getErrorFace();
//                    ToastUtils.showLong("当前总成功人脸数---------" + success + "--------当前总失败人脸数-----" + error);
                }
//                else {
//                    BaseConstant.IS_FACE_COMMIT = true;
//                    setFaceSuccess();
//                }
            }
        });
    }


    /**
     * 十分钟为单位轮询接口
     */
    protected synchronized void syncData() {

        if (syncDataDisposable != null) {
            syncDataDisposable.dispose();
        }

        Observable.interval(2, 2, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        syncDataDisposable = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        updateData();

                    }

                });

    }

    @Subscriber(tag = BaseConstant.NETWORK_CHANGE)
    private void onNetworkChange(int type) {
        onPhoneNetworkChange(type);
    }

    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);

        if (syncDataDisposable != null) {
            syncDataDisposable.dispose();
        }

        if (netBroadcastReceiver != null) {
            unregisterReceiver(netBroadcastReceiver);
        }

        if(serialPortUtils!=null){
            serialPortUtils.closeSerialPort();
        }

        super.onDestroy();
    }
}
