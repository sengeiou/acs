package com.tifenbao.acs.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.View;

import com.tifenbao.mvvmhabit.base.BaseApplication;
import com.tifenbao.mvvmhabit.base.BaseViewModel;
import com.tifenbao.mvvmhabit.utils.ToastUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import okhttp3.Dns;

/**
 * Created by mar on 2018/6/21.
 */

public class SettingViewModel extends BaseViewModel {

    public ObservableField<String> Ipaddress = new ObservableField();//Ip地址
    public ObservableField<String> IMEIaddress = new ObservableField();//Imei地址

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public void setData(){

        Ipaddress.set(getLocalIpAddress());
        IMEIaddress.set(Settings.Secure.getString(BaseApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface ni = en.nextElement();
                Enumeration<InetAddress> enIp = ni.getInetAddresses();
                while (enIp.hasMoreElements()) {
                    InetAddress inet = enIp.nextElement();
                    if (!inet.isLoopbackAddress()
                            && (inet instanceof Inet4Address)) {
                        return inet.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "0";
    }

    @Override
    public void onDestroy() {

        //RxBus解耦组件通信
//        RxBus.getDefault().post(new _Data(dataObservable.get()));
        super.onDestroy();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
