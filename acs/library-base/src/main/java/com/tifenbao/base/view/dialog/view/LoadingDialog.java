package com.tifenbao.base.view.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tifenbao.base.R;

/**
 * mar
 * 2019/9/9
 */
public class LoadingDialog extends BaseDialog {

    public static LoadingDialog loginDialog;

    public static LoadingDialog getInstance() {
        if (loginDialog == null) {
            loginDialog = new LoadingDialog();
        }
        return loginDialog;
    }

    /**
     * Loading弹窗
     */
    public Dialog getLoadingDialog(final Context context) {

        cancel();
        this.context = context;
        View dialogview = View.inflate(context, R.layout.dialog_loading, null);
        dialog = new Dialog(context, R.style.loaddialog);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(dialogview);
        dialog.getWindow().setLayout(getWidth(context, 1f), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        dialog.show();

        return dialog;
    }

}
