package com.tifenbao.acs.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tifenbao.acs.activity.MainActivity;

/**
 * 开机自启动接受广播
 * <p>
 * mar
 * 2019/9/15
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent thisIntent = new Intent(context, MainActivity.class);
            thisIntent.setAction("android.intent.action.MAIN");
            thisIntent.addCategory("android.intent.category.LAUNCHER");
            thisIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(thisIntent);
        }
    }

}
