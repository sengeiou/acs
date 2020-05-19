package com.tifenbao.base.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.mvvmhabit.http.NetworkUtil;

import org.simple.eventbus.EventBus;

import java.lang.ref.WeakReference;

/**
 * Created by shaun on 21/09/2017.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mMyHandler.sendEmptyMessageDelayed(NetworkUtil.getNetState(context), 1000);
            }
        }).start();

    }

    private MyHandler mMyHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<NetWorkStateReceiver> mActivity;

        MyHandler(NetWorkStateReceiver activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            NetWorkStateReceiver activity = mActivity.get();
            EventBus.getDefault().post(msg.what, BaseConstant.NETWORK_CHANGE);
        }
    }
}
