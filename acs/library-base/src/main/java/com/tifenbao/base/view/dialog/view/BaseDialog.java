package com.tifenbao.base.view.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tifenbao.base.util.PublicMethod;

/**
 * mar
 * 2019/8/24
 */
public class BaseDialog {

    protected Dialog dialog;
    protected Context context;

    protected Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            cancel();

        }
    };


    protected void showDialog(Context context, View view, int width) {
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(view);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 1000 * 10);
        }
    }


    protected int getWidth(Context context, float size) {
        return (int) (PublicMethod.getPhoneWidth(context) / size);
    }

    public boolean isShow() {
        if (dialog == null) {
            return false;
        }
        return dialog.isShowing();
    }


    public synchronized void cancel() {
        if (context != null && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            if (handler != null) {
                handler.removeMessages(1);
            }
        }
    }
}
