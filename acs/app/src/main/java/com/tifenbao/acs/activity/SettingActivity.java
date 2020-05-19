package com.tifenbao.acs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.tifenbao.acs.BR;
import com.tifenbao.acs.R;
import com.tifenbao.acs.bean.CodeBean;
import com.tifenbao.acs.databinding.ActivitySettingBinding;
import com.tifenbao.acs.mode.AcsHttpDataUtils;
import com.tifenbao.acs.utils.updateversion.VersionUpdate;
import com.tifenbao.acs.viewmodel.SettingViewModel;
import com.tifenbao.base.base.BaseLandscapeActivity;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseResult;
import com.tifenbao.base.util.CacheUtils;
import com.tifenbao.mvvmhabit.base.BaseActivity;
import com.tifenbao.mvvmhabit.utils.ToastUtils;

/**
 * 设置页面
 * <p>
 * mar
 * 2020/5/14
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> implements View.OnClickListener {

    private EditText schoolNumEdit;//学校编号

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
        initView();
        viewModel.setData();
    }


    public void initView() {
        schoolNumEdit = findViewById(R.id.school_num_edit);
        findViewById(R.id.binding).setOnClickListener(this);
        findViewById(R.id.back_text).setOnClickListener(this);
        findViewById(R.id.back_img).setOnClickListener(this);
        findViewById(R.id.checkin_update).setOnClickListener(this);
        findViewById(R.id.system_setting).setOnClickListener(this);

        if(!TextUtils.isEmpty(CacheUtils.get(SettingActivity.this).getAsString(CacheUtils.SCHOOL_NUM))){
            schoolNumEdit.setText(CacheUtils.get(SettingActivity.this).getAsString(CacheUtils.SCHOOL_NUM));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_text:
            case R.id.back_img:

                if(isBack()){
                   finish();
                }

                break;
            case R.id.checkin_update:
                String schoolNme=CacheUtils.get(SettingActivity.this).getAsString(CacheUtils.SCHOOL_NUM);

                if(TextUtils.isEmpty(schoolNme)){
                    return;
                }
                VersionUpdate versionUpdate = new VersionUpdate();
                versionUpdate.updateVersions("151",viewModel.getLifecycleProvider(), true);
                break;

            case R.id.system_setting:

                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                break;

            case R.id.binding:
                String num = schoolNumEdit.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请输入编号");
                    return;
                }

                AcsHttpDataUtils.querySDKCode(num, viewModel.getLifecycleProvider(), new BaseObserver<BaseResult<CodeBean>>() {

                    @Override
                    public void onSuccess(BaseResult<CodeBean> responseData) {

                        if (responseData != null&&responseData.data!=null&&!TextUtils.isEmpty(responseData.data.getCode())) {
                            CacheUtils.get(SettingActivity.this).put(CacheUtils.SCHOOL_NUM, num);
                            CacheUtils.get(SettingActivity.this).put(CacheUtils.SDK_CODE, responseData.data.getCode());
                            ToastUtils.showShort(responseData.message);
                            setResult(RESULT_OK);
                        }
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                });

                break;


        }
    }


    private boolean isBack(){

        String sdkcode=CacheUtils.get(SettingActivity.this).getAsString(CacheUtils.SDK_CODE);

        if(TextUtils.isEmpty(sdkcode)){
            return false;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(!isBack()){
            return true;
        }

        return super.onKeyDown(keyCode,event);
    }
}
