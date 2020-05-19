package com.tifenbao.newfacemanager;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tifenbao.facemanager.R;

public class NativeFaceCameraDialogUtils {

    private NativeFaceCameraDialog dialog;


    public NativeFaceCameraDialogUtils() {

    }

    /**
     * databaseurl  数据库地址
     * recognizeTime 识别时间
     * autoClose    是否设置识别完成自动关闭/0默认开启
     */
    public void setCameradialog(Activity acitvity, int autoCLose, NativeFaceCameraDialogCallback callback) {

        dialog = new NativeFaceCameraDialog(acitvity, autoCLose, R.style.loaddialog, callback);

        showDialog(acitvity, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.show();
    }

    private void showDialog(Context context, int width) {
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        dialog.setContentView(view);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
    }


    public void cancel() {

        if (dialog != null) {
            dialog.dismiss();
        }

    }

}
