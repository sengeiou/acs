package com.tifenbao.base.view.dialog.view;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tifenbao.base.bean.card.CardStudentBean;
import com.tifenbao.base.R;

public class CameraDialogUtils extends BaseDialog {

    private CameraDialog dialog;

    public CameraDialogUtils() {

    }

    public void setCameradialog(Activity acitvity, String cardId, CardStudentBean studentBean, String time, String status, String classCamera) {

        dialog = new CameraDialog(acitvity, R.style.loaddialog, cardId, studentBean.getClass_name(), studentBean.getStudent_name(), time, status, studentBean.getPhoto(), classCamera);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setLayout(getWidth(acitvity, 1.5f), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();

//        dialog.show();
    }

    public void cancelCameradialog() {
        dialog.dismiss();
    }


}
