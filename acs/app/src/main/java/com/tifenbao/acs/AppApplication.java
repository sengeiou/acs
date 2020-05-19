package com.tifenbao.acs;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.rockchip.rkapi.RKapi;
import com.tencent.bugly.crashreport.CrashReport;
import com.tifenbao.base.config.ModuleLifecycleConfig;
import com.tifenbao.base.util.CacheUtils;
import com.tifenbao.mvvmhabit.base.BaseApplication;

/**
 * Created by goldze on 2018/6/21 0021.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);

        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ebf653d");
        CrashReport.initCrashReport(getApplicationContext(), "ad9e556f36", false);
        RKapi.InitApi(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
