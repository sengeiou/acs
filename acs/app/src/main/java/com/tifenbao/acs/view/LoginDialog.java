package com.tifenbao.acs.view;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tifenbao.acs.R;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.view.dialog.view.BaseDialog;
import com.tifenbao.mvvmhabit.utils.ToastUtils;

/**
 * 弹窗
 *
 * mar
 * 2020/5/14
 */
public class LoginDialog extends BaseDialog {

    private EditText userEdit;
    public static LoginDialog loginDialog;

    public static LoginDialog getInstance() {
        if (loginDialog == null) {
            loginDialog = new LoginDialog();
        }
        return loginDialog;
    }
    /**
     * 登录弹窗
     */
    public Dialog getLoginDialog(Context context, final DialogOnClickListener listener) {
        cancel();

        this.context = context;

        View dialogview = View.inflate(context, R.layout.dialog_login, null);

        userEdit = dialogview.findViewById(R.id.user_edit);

        userEdit.addTextChangedListener(textWatcher);

        dialogview.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText=userEdit.getText().toString();
                if (TextUtils.isEmpty(editText)) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }
                if (!BaseConstant.BASE_COMMAND.equals(editText)) {
                    ToastUtils.showShort("口令错误");
                    return;
                }
                listener.loginClick(userEdit.getText().toString());

                dialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        dialog = new Dialog(context, com.tifenbao.base.R.style.loaddialog);

        showDialog(context, dialogview, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (handler != null) {
                handler.removeMessages(1);
                handler.sendEmptyMessageDelayed(1, 1000 * 10);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    public interface DialogOnClickListener {


        void loginClick(String password);

        void cancelClick();
    }

}
