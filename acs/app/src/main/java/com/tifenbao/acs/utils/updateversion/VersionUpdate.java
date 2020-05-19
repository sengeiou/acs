package com.tifenbao.acs.utils.updateversion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.tifenbao.acs.BuildConfig;
import com.tifenbao.base.base.BaseLandscapeActivity;
import com.tifenbao.base.bean.version.VersionBean;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseResult;
import com.tifenbao.base.util.FileUtils;
import com.tifenbao.mvvmhabit.base.AppManager;
import com.tifenbao.mvvmhabit.base.BaseApplication;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 版本更新
 *
 * @author mar
 */
public class VersionUpdate {
    private HttpUtils httpUtils = null;
    private BaseLandscapeActivity activity = null;
    private File apkFile = null;
    private UpdateCallback updateCallback = null;

    public interface UpdateCallback {
        public void onCheckCompleted(int status);
    }

    public VersionUpdate() {

    }

    public VersionUpdate(BaseLandscapeActivity activity, UpdateCallback updateCallback) {
        this.activity = activity;
        this.updateCallback = updateCallback;

        httpUtils = new HttpUtils();
    }

//    private OnClickListener onClickListener = new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            updateDialog.dismiss();
//            activity.finish();
//        }
//    };

    public void updateVersions(String schoolNum,LifecycleProvider lifecycleProvider, boolean isToast) {

        UpdateHttpDataUtils.getIndexExamData(schoolNum,lifecycleProvider, new BaseObserver<BaseResult<VersionBean>>() {
            @Override
            public void onSuccess(BaseResult<VersionBean> response) {

                if (!TextUtils.isEmpty(response.message) && isToast) {
                    ToastUtils.showShort(response.message);
                }

                if (response != null && response.data != null) {

                    if(BuildConfig.VERSION_CODE<response.data.getVersion_code()){
                        ToastUtils.showShort("当前已是最新版本");
                        return;
                    }

                    if (!TextUtils.isEmpty(response.data.getDownload_url())) {
                        httpUtils = new HttpUtils();
                        downloadAPK(response.data.getDownload_url(), response.data.getVersion());
                    }

                }

            }
        });

    }

    public void updateVersions() {
        httpUtils.send(HttpMethod.GET, null, updateVersionsCallBack);
    }

//    public void updateVersions(boolean isShowHintDialog) {
//        this.isShowHintDialog = isShowHintDialog;
//        updateVersions();
//    }

    public void downloadAPK(String url, String versionName) {

        if (FileUtils.getFilename("bbt_" + versionName + ".apk") != null) {

            FileUtils.delete(new File(BaseApplication.getInstance()
                    .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                    "bbt_" + versionName + ".apk"));
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                httpUtils.download(
                        url,
                        new File(BaseApplication.getInstance()
                                .getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                                "bbt_" + versionName + ".apk").toString(),
                        true, downloadCallBack);
            }
        }).start();

    }

    private RequestCallBack<String> updateVersionsCallBack = new RequestCallBack<String>() {

        @Override
        public void onStart(int requestCode) {
//            if (isShowHintDialog) {
////				activity.proDialog.setMessage("正在获取更新...");
//                activity.proDialog.show();
//            }
        }

        @Override
        public void onFailure(int requestCode, HttpException arg0, String arg1) {
//            if (isShowHintDialog) {
//                activity.proDialog.dismiss();
//                activity.show("网络异常，更新失败！");
//                if (updateCallback != null) {
//                    updateCallback.onCheckCompleted(-1);
//                }
//            }
        }

        @Override
        public void onSuccess(int requestCode, ResponseInfo<String> arg0) {
            LogUtils.i("Update Versions-->" + arg0.result);

            if (updateCallback != null) {
                updateCallback.onCheckCompleted(0);
            }
        }

    };

    private RequestCallBack<File> downloadCallBack = new RequestCallBack<File>() {

        @Override
        public void onSuccess(int requestCode, ResponseInfo<File> arg0) {
            apkFile = arg0.result;

            if (apkFile != null && apkFile.isFile()) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(apkFile),
                        "application/vnd.android.package-archive");
                AppManager.getAppManager().currentActivity().startActivity(intent);

            } else {
//                ToastUtils.showShort("没有找到安装文件！");
            }
        }

        @Override
        public void onFailure(int requestCode, HttpException arg0, String arg1) {
//            updateDialog.dismiss();
////			downloadAPK(url, versionName);
//            activity.show("下载失败！");
        }

        @Override
        public void onLoading(int requestCode, long total, long current,
                              boolean isUploading) {
            int proNum = (int) ((double) current / (double) total * 100f);
//            progressNumber_text.setText(proNum + "/100");
//
//            downloadProgress.setProgress(proNum);
        }

        @Override
        public void onStart(int requestCode) {
//            updateDialog.show();
        }
    };

    // 静默安装，1-安装成功，或没有升级文件，2-升级安装出现异常，-1-程序异常
    public int installBySlient(Context context, File file) {
        int result = 0;
        try {
            if (file == null
                    || file.length() <= 0 || !file.exists() || !file.isFile()) {
                return 1;
            }
            //String[] args = { "pm", "install", "-r", filePath };
            //String[] args = { "pm", "install","-i ","包名", "-r",filePath }; //适用7.0
            String[] args = {"pm", "install", "-i", "包名", "--user", "0", file.getAbsolutePath()};
            ProcessBuilder processBuilder = new ProcessBuilder(args);
            Process process = null;
            BufferedReader successResult = null;
            BufferedReader errorResult = null;
            StringBuilder successMsg = new StringBuilder();
            StringBuilder errorMsg = new StringBuilder();
            try {
                process = processBuilder.start();
                successResult = new BufferedReader(new InputStreamReader(
                        process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(
                        process.getErrorStream()));
                String s;

                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }

                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = 2;
            } catch (Exception e) {
                e.printStackTrace();
                result = 2;
            } finally {
                try {
                    if (successResult != null) {
                        successResult.close();
                    }
                    if (errorResult != null) {
                        errorResult.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
            }

            if (successMsg.toString().contains("Success")
                    || successMsg.toString().contains("success")) {
                result = 1;
                ToastUtils.showShort("安装成功result = 1");
            } else {
                result = 2;
            }
            ToastUtils.showLong("App升级信息:" + "successMsg:" + successMsg + ", ErrorMsg:" + errorMsg);
        } catch (Exception e) {
            result = -1;
        }
        return result;
    }

}