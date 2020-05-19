package com.tifenbao.newfacemanager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import com.xinshi.android.face.camera.FaceCamera;
import com.xinshi.android.face.data.FaceData;
import com.xinshi.android.face.data.LivenessDetectMode;
import com.xinshi.android.face.db.DbFaceItem;
import com.xinshi.android.face.db.DbHelper;
import com.xinshi.android.face.db.DbPerson;
import com.xinshi.android.face.exceptions.FaceException;
import com.xinshi.android.face.exceptions.SDKAuthException;
import com.xinshi.android.face.exceptions.SDKException;
import com.xinshi.android.face.http.HttpUtils;
import com.xinshi.android.face.image.RGBImage;
import com.xinshi.android.face.jni.Tool;
import com.xinshi.android.face.model.FaceSDK;
import com.xinshi.android.face.view.SingleCameraView;
import com.xinshi.android.xsfacesdk.XsFaceSDK;
import com.xinshi.android.xsfacesdk.helper.XsFaceSDKHelper;
import com.xinshi.android.xsfacesdk.helper.XsFaceSDKInitConfigParams;
import com.xinshi.android.xsfacesdk.helper_v3.XsFaceSDKSceneHelper;
import com.xinshi.android.xsfacesdk.scene.CommonFaceRecScene;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 人脸识别工具类
 */
public class FaceManagerUtils {
    static final String TAG = "FaceManagerUtils";
    static final String DOWNLOAD_CONFIG_FILE = "device_config.json";
    static final String ENV_CONFIG_FILE = "xs_device.json";
    static final String SDK_CONFIG_FILE = "xs_sdk.json";
    static final String LICENSE_FILE = "xs_license.txt";
    protected static final int PERMISSIONS_REQUEST_CODE = 99;
    public static final double[] SIM_THRESHOLDS = {-1, 0, 0.34, 0.41, 0.48, 0.56, 1}; //v4 青少年
    Activity activity;
    FaceMangerCallback callback;
    static boolean isConfiged = false;
    static AtomicBoolean isAuthed = new AtomicBoolean(false);
    public static String faceImageRootPath;
    static String downloadConfigFile;
    static String deviceId;

    /**
     * 初始化
     *
     * @param activity
     * @param callback
     */
    public void init(Activity activity, FaceMangerCallback callback) {
        this.activity = activity;
        this.callback = callback;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = checkOrAddPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE
            );
            if (permissions != null && permissions.length > 0) {
                activity.requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
                return;
            }
        }
        configSDK(activity);
        initHandleThread();
    }

    private String[] checkOrAddPermissions(String... permissions) {
        List<String> ret = new ArrayList<>();
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    ret.add(permission);
                }
            }
        }
        return ret.toArray(new String[0]);
    }

    static synchronized void configSDK(final Activity activity) {
        if (!isConfiged) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    XsFaceSDKInitConfigParams params = new XsFaceSDKInitConfigParams();
//                    params.setSerialType(2);
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    faceImageRootPath = String.format("%s/face", path);
                    downloadConfigFile = String.format("%s/%s", path, DOWNLOAD_CONFIG_FILE);

                    params.setRootPath(String.format("%s/xinshi_models", path));

                    params.setEnvConfigFile(String.format("%s/xinshi/%s", path, ENV_CONFIG_FILE));
                    params.setSdkConfigFile(String.format("%s/xinshi/%s", path, SDK_CONFIG_FILE));
                    params.setLicenseFile(String.format("%s/%s", path, LICENSE_FILE));
                    XsFaceSDKHelper.config(activity, params);
                    deviceId = XsFaceSDK.getDeviceID();
                    try {
                        File v4File = new File(String.format("%s/xinshi/v4.data", path));
                        if (!v4File.exists()) {
                            File dbFile = new File(activity.getDatabasePath("faceperson").getPath());
                            if (dbFile.exists()) {
                                dbFile.delete();
                            }
                            Tool.saveData("isv4".getBytes(), v4File);
                        }
                    } catch (Throwable e) {
                        Log.d(TAG, "process v4 db file error:", e);
                    }
                    DbHelper.instance = new DbHelper(activity);
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isConfiged = true;
        }
    }

    Handler handler;
    HandlerThread handlerThread;

    /**
     * 初始化异步处理线程
     */
    synchronized void initHandleThread() {
        if (handlerThread != null) return;
        handlerThread = new HandlerThread("com/tifenbao/facemanager", 8);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:// 初始化成功
                        initSuccess();
                        break;
                    case 1:// 添加人脸
                        asyncAddFace((AddFaceParams) msg.obj);
                        break;
                    case 2://删除人脸
                        try {
                            DbHelper.instance.clearPersons();
                            XsFaceSDKHelper.clearPersons();
                            callback.DeleteCallbackCode(NativeFaceConstant.ALL_DELETE_SUCCESS, "");
                        } catch (Exception e) {

                        }


                        break;
                    case 3://下载配置
                        downloadConfig((String) msg.obj);
                        break;
                }
            }

            /**
             * 下载配置
             * @param url
             */
            private void downloadConfig(String url) {
                File file = new File(downloadConfigFile);
                try {
                    if (file.exists()) {
                        if (callback != null)
                            callback.DownLoadConfigFileCallback(NativeFaceConstant.SUCCESS);
                        return;
                    }
                    HttpUtils.download(TAG, url, file);
                    Log.d(TAG, String.format("配置文件下载成功: %s", url));
                    loadConfig();
                    Log.d(TAG, String.format("装入配置文件成功"));
                    callback.DownLoadConfigFileCallback(NativeFaceConstant.SUCCESS);
                } catch (Throwable e) {
                    Log.d(TAG, "下载配置文件失败", e);
                    file.delete();
                    if (callback != null)
                        callback.DownLoadConfigFileCallback(NativeFaceConstant.ERROR);
                }
            }

            /**
             * 初始化成功
             */
            synchronized void initSuccess() {
                try {

//                    loadConfig();
                    XsFaceSDK.instance.getFaceSearchLibrary().clearPersons();
                    DbHelper.instance.addToFaceSearchLibrary(XsFaceSDK.instance.getFaceSearchLibrary());
                    if (callback != null)
                        callback.successCode("初始化成功了");
                } catch (Throwable e) {
                    Log.d(TAG, "add error:", e);
                    if (callback != null)
                        callback.ErrorCode("初始化搜索库失败");
                }
            }

            synchronized void asyncAddFace(AddFaceParams obj) {

                if(TextUtils.isEmpty(obj.url)){
                    if (callback != null) {
                        callback.DownLoadCallbackCode( NativeFaceConstant.ERROR,
                               "", obj.id, -1);
                    }
                    return;
                }

                File file = new File(faceImageRootPath, obj.id + ".jpg");
                boolean downloaded = false;
                try {
                    file.getParentFile().mkdirs();
                    if (obj.url.startsWith(faceImageRootPath)) {
                        file = new File(obj.url);
                    } else
                        HttpUtils.download(TAG, obj.url, file);
                    downloaded = true;
                    Bitmap bitmap = Tool.loadBitmap(file);
                    RGBImage image = RGBImage.bitmapToRGBImage(bitmap);
                    FaceData f = FaceData.getMaxFace(XsFaceSDKHelper.faceDetect(image));
                    if (f == null)
                        throw new FaceException("找不到人脸");
                    float[] feature = XsFaceSDKHelper.extractFaceFeature(image, f);
                    DbPerson person = DbHelper.instance.findPersonByCode(obj.id);
                    DbFaceItem faceItem = new DbFaceItem(null, feature, file.getName());
                    if (person != null) {
                        DbHelper.instance.updatePersonFace(person.getPersonId(), faceItem, null, 0);
                        XsFaceSDK.instance.getFaceSearchLibrary().addOrReplaceFace(person.getPersonId(), person.getPersonId() * 2L, feature);
                    } else {
                        person = new DbPerson(-1, obj.username, faceItem, null, 0);
                        person.setPersonCode(obj.id);
                        DbHelper.instance.putPerson(person);
                        faceItem.setFaceId(person.getPersonId() * 2);
                        XsFaceSDK.instance.getFaceSearchLibrary().addPerson(person);
                    }
                    if (callback != null)
                        callback.DownLoadCallbackCode(NativeFaceConstant.SUCCESS, file.getAbsolutePath(), obj.id, person.getPersonId());
                    Log.d(TAG, String.format("Face added(%s,%s,%s)", obj.url, obj.username, obj.id));
                } catch (Throwable e) {
                    Log.d(TAG, String.format("Face added(%s,%s,%s) error:", obj.url, obj.username, obj.id), e);
                    if (callback != null) {
                        callback.DownLoadCallbackCode(downloaded ? NativeFaceConstant.ERROR : NativeFaceConstant.UNNETWORK,
                                file.getAbsolutePath(), obj.id, -1);
                    }
                }
            }
        };
    }

    public synchronized void loadConfig() throws IOException, JSONException, SDKException {
        File file = new File(downloadConfigFile);
        String jsonString = new String(Tool.loadData(file), "utf-8");
        Log.d(TAG, String.format("load config: %s", jsonString));
        JSONObject jsonObject = new JSONObject(jsonString);
        try {
            if (jsonString.contains("\"best_algo_size\"") && jsonString.contains("\"preview_size\"")) {
                XsFaceSDKHelper.getEnvConfig().load(jsonObject);
                XsFaceSDKHelper.getEnvConfig().save();
                Log.d(TAG, String.format("装入新版配置文件成功"));
            } else {
                OldDeviceConfig deviceConfig = OldDeviceConfig.loadFromString(jsonString);
                if (deviceConfig != null) {
                    deviceConfig.copyConfigToEnvConfig(activity, XsFaceSDKHelper.getEnvConfig());
                    Log.d(TAG, String.format("装入旧版配置文件成功"));
                } else
                    throw new FaceException("解析旧版适配器文件失败");
            }
        } catch (Throwable e) {
            Log.d(TAG, String.format("解析旧版文件失败，尝试用新版配置文件装载"));
            //用新版适配器文件再次解析
            XsFaceSDKHelper.getEnvConfig().loadFrom(jsonObject);
            XsFaceSDKHelper.getEnvConfig().save();
            Log.d(TAG, String.format("装入新版配置文件成功"));
        }
    }

    /**
     * 输入授权验证码
     */
    public synchronized void Auth(String number) {
        if (isAuthed.get()) {
            handler.sendEmptyMessage(0);
            return;
        }
        try {
            if (number != null && !number.isEmpty()) {
                XsFaceSDKHelper.getSDKConfig().setLicenseKey(number);
                XsFaceSDKHelper.getSDKConfig().save();
            }
            XsFaceSDKHelper.init(activity, new FaceSDK.InitCallback() {
                @Override
                public void onInitSuccess() {
                    isAuthed.set(true);
                    handler.sendEmptyMessage(0);
                }

                @Override
                public void onInitFailed(Throwable throwable) {
                    try {
                        Log.d(TAG, "init failed:", throwable);
                        if (callback != null) {
                            if (throwable != null && throwable instanceof SDKAuthException) {
                                callback.setDeviceID(TextUtils.isEmpty(number) ? 0 : 1, throwable.getMessage(), deviceId);
                            } else
                                callback.setDeviceID(TextUtils.isEmpty(number) ? 0 : 1, String.format("意外错误: %s", throwable.getMessage()), deviceId);
                        }
                    } catch (Throwable e) {
                    }
                }
            });
        } catch (Throwable e) {
            Log.d("FaceManagerUtils", "initSDK error:", e);
        }
    }

    /**
     * 初始化SDK
     */
    public synchronized void initSDK() {
        Auth(null);
    }

    class AddFaceParams {
        String url, username, id;

        public AddFaceParams(String url, String username, String id) {
            this.url = url;
            this.username = username;
            this.id = id;
        }
    }

    /**
     * 添加人脸图片
     */
    public synchronized void AddFace(String url, String username, String id) {
        handler.sendMessage(handler.obtainMessage(1, new AddFaceParams(url, username, id)));
    }


    /**
     * 删除人脸图片
     */
    public synchronized void deleteFace(String id) {
        handler.sendMessage(handler.obtainMessage(2, id));
    }

    public synchronized void deleteAllFace() {
        handler.sendMessage(handler.obtainMessage(2));
    }

    /**
     * 下载配置文件
     */
    public void initConfigFile(String url) {
        handler.sendMessage(handler.obtainMessage(3, url));
    }

    /**
     * 读取配置文件
     */
    public synchronized void getConfigFile() {
        File configFile = new File(downloadConfigFile);
        try {
            if (configFile.exists()) {
                callback.initConfigFile(NativeFaceConstant.SUCCESS_FILE, Build.BOARD);
            } else {
                callback.initConfigFile(NativeFaceConstant.ERROR_FILE, Build.BOARD);
            }
        } catch (Exception e) {
            callback.initConfigFile(NativeFaceConstant.ERROR_FILE, Build.BOARD);
        }
    }

    static public CommonFaceRecScene createFaceRecScene(SingleCameraView cameraView, int liveness, CommonFaceRecScene.CommonFaceRecSceneCallback callback) throws FaceException, IOException {
        CommonFaceRecScene.CommonFaceRecSceneParams params = new CommonFaceRecScene.CommonFaceRecSceneParams();
        params.livenessDetectMode = getLiveness(liveness);
        params.normalizeThresholds = null;
        params.maxSearchFailTimes = 3;
        FaceCamera faceCamera = XsFaceSDKHelper.getEnvConfig().getVisCamera();
        cameraView.setCamera(faceCamera);
        return XsFaceSDKSceneHelper.createCommonFaceRecScene(cameraView, params, callback);
    }


    static private LivenessDetectMode getLiveness(int liveness) {

        switch (liveness) {
            case 1:
                return LivenessDetectMode.NONE;
            case 2:
                return LivenessDetectMode.VIS_LIVENESS;
            case 3:
                return LivenessDetectMode.VIS_LIVENESS_PHONE;
            case 4:
                return LivenessDetectMode.NIR_LIVENESS_PHONE;
            case 5:
                return LivenessDetectMode.NIR_LIVENESS;
            case 6:
                return LivenessDetectMode.DEPTH_LIVENESS;
        }
        return LivenessDetectMode.VIS_LIVENESS;
    }


}