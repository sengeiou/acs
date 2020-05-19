package com.tifenbao.base.view.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tifenbao.base.R;

/**
 * mar
 * 2019/8/24
 */
public class NoFaceDialog extends BaseDialog {

    /**
     * 没有人脸时弹窗
     */
    public Dialog getNoFaceDialog(final Context context) {

        this.context = context;
        View dialogview = View.inflate(context, R.layout.dialog_noface, null);

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.loaddialog);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(dialogview);
        dialog.getWindow().setLayout(getWidth(context, 1.5f), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();

        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 1000 * 3);
        }


        return dialog;
    }

}
