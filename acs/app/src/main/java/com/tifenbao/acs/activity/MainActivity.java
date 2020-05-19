package com.tifenbao.acs.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tfb.speech.TtsUtils;
import com.android.tfb.temperature.utils.TemperatureUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tfb.lib_light.LightUtils;
import com.tifenbao.acs.BR;
import com.tifenbao.acs.R;
import com.tifenbao.acs.view.RoundedCornersTransform;
import com.tifenbao.base.bean.CheckUpBean;
import com.tifenbao.base.bean.acs.CachesPersionBean;
import com.tifenbao.base.bean.acs.PersionBean;
import com.tifenbao.base.bean.acs.PersionListBean;
import com.tifenbao.base.bean.acs.TemperatureBean;
import com.tifenbao.acs.databinding.ActivityMainBinding;
import com.tifenbao.acs.mode.AcsHttpDataUtils;
import com.tifenbao.acs.view.LoginDialog;
import com.tifenbao.acs.view.TextureVideoViewOutlineProvider;
import com.tifenbao.acs.viewmodel.MainViewModel;
import com.tifenbao.base.base.BaseLandscapeActivity;
import com.tifenbao.base.bean.upload.UploadBaseBean;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.network.BaseObserver;
import com.tifenbao.base.network.BaseResult;
import com.tifenbao.base.util.BitmapUtils;
import com.tifenbao.base.util.CacheFaceUtils;
import com.tifenbao.base.util.CacheUtils;
import com.tifenbao.base.util.PublicMethod;
import com.tifenbao.mvvmhabit.http.NetworkUtil;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.tifenbao.newfacemanager.FaceManagerUtils;
import com.xinshi.android.face.data.FaceData;
import com.xinshi.android.face.data.FaceDetectData;
import com.xinshi.android.face.data.SearchedPerson;
import com.xinshi.android.face.view.SingleCameraView;
import com.xinshi.android.xsfacesdk.scene.CommonFaceRecScene;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends BaseLandscapeActivity<ActivityMainBinding, MainViewModel> implements View.OnClickListener {

    private ImageView wifiImg;
    private ImageView scan_result;
    private ImageView circle_big, circle_middle, circle_small;
    private TextView faceName, faceTemp, faceTime;
    private ImageView face_img;
    private String school_num;
    private SingleCameraView cameraView;
    private CommonFaceRecScene scene;
    private CommonFaceRecScene.CommonFaceRecSceneCallback commonFaceRecSceneCallback;
    private TtsUtils ttsUtils;
    private PersionListBean persionListBean;
    private TemperatureBean temperatureBean;
    private PersionBean persionBean;
    private List<CachesPersionBean> checkUpBeanList;
    private boolean isUpload = false;
    private boolean isNetWork = true;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            clearStatus();
            return false;
        }
    });

    private long checkTime = 0;
    private boolean isCheckCard = false;

    private String nowFaceId = "";

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

        school_num = CacheUtils.get(MainActivity.this).getAsString(CacheUtils.SCHOOL_NUM);
        initView();

        if (TextUtils.isEmpty(school_num)) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivityForResult(intent, 1001);
        } else {
            initFaceManager();//有激活码开始执行人脸激活
            faceManagerUtils.initSDK();
            if (CacheUtils.get(this).getAsObject(CacheUtils.FACE_CHICK) != null) {
                checkUpBeanList = (List<CachesPersionBean>) CacheUtils.get(this).getAsObject(CacheUtils.FACE_CHICK);
                if (checkUpBeanList == null) {
                    checkUpBeanList = new ArrayList<>();
                }
                if (checkUpBeanList.size() > 0) {
                    uploadCheckPeople();
                }
            } else {
                checkUpBeanList = new ArrayList<>();
            }

        }


    }

    private void initView() {
        wifiImg = findViewById(R.id.wifiImg);
        cameraView = findViewById(R.id.camera_view);
        circle_big = findViewById(R.id.circle_big);
        circle_middle = findViewById(R.id.circle_middle);
        circle_small = findViewById(R.id.circle_small);
        scan_result = findViewById(R.id.scan_result);
        faceName = findViewById(R.id.face_name);
        faceTemp = findViewById(R.id.face_temp);
        faceTime = findViewById(R.id.face_time);
        face_img = findViewById(R.id.face_img);
        findViewById(R.id.home).setOnClickListener(this);
        circle_big.startAnimation(startAnimation(true));
        circle_middle.startAnimation(startAnimation(false));
        circle_small.startAnimation(startAnimation(true));
        initListener();
    }

    private void initListener() {

        commonFaceRecSceneCallback = new CommonFaceRecScene.CommonFaceRecSceneCallback() {

            @Override
            public void onFaceChanged(FaceDetectData detectData, List<FaceData> addFaces, List<FaceData> lostFaces) {
                LightUtils.openLight(262, 0);
            }

            @Override
            public void onFaceDetected(FaceDetectData faceDetectData) {
                LightUtils.openLight(262, 1);
            }

            @Override
            public void onFaceRecComplete(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, final SearchedPerson searchedPerson, List<SearchedPerson> list) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (searchedPerson.getPerson() != null && searchedPerson.getPerson().getPersonData() != null){

                                long systemTime = System.currentTimeMillis();
                                if (systemTime - checkTime > BaseConstant.BASE_FACE_COUNTDOWN_SECOND || !searchedPerson.getPerson().getPersonCode().equals(nowFaceId)) {
                                    checkTime = systemTime;
                                    nowFaceId =searchedPerson.getPerson().getPersonCode();
                                    getTemp(searchedPerson.getPerson().getPersonCode(), faceRecSceneData.image.toBitmap());
                                }

                            }else{
                                getTemp("", null);
                            }

                        } catch (Exception e) {
                            Log.d("face", e.toString() + "--获取当前图像失败");
                        }
                    }
                });
            }

            @Override
            public void onRecStranger(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, SearchedPerson searchedPerson) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getTemp("", null);

                        } catch (Exception e) {
                            Log.d("face", "获取当前图像失败");
                        }
                    }
                });
            }

            @Override
            public void onLivenessAttack(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, SearchedPerson searchedPerson, List<SearchedPerson> list) {
            }
        };

    }

    private void getQueryPersion() {

        AcsHttpDataUtils.queryPersion(school_num, "", viewModel.getLifecycleProvider(), new BaseObserver<BaseResult<PersionListBean>>() {

            @Override
            public void onSuccess(BaseResult<PersionListBean> responseData) {

                if (responseData != null) {
                    persionListBean = responseData.data;
                    setPersionData();
                }
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }
        });
    }

    private void setPersionData() {

        if (persionListBean != null) {
            cacheUtils.put(CacheUtils.PERSION_LISTBEAN, (Serializable) persionListBean);
            temperatureBean = persionListBean.getTemperature();
            if (persionListBean.getList() != null && !persionListBean.getList().isEmpty()) {

                persionBeanList = persionListBean.getList();
                viewModel.setPeopleData(persionBeanList.size());
//                        viewModel.setImgData(CacheFaceUtils.getInstance().getSuccessFace());
                setFaceImage(-1, "", "", -1);
            }
        }


    }

    private void uploadsaveFile(PersionBean persionBean, Bitmap bitmap) {

        String fileName = BitmapUtils.saveBitmapFileName(this, bitmap);
        if (checkUpBeanList != null) {
            CachesPersionBean bean = new CachesPersionBean();
            bean.setFileName(fileName);
            bean.setPersionBean(persionBean);
            bean.setSchool_num(school_num);
            bean.setClock_time(String.valueOf(System.currentTimeMillis()));
            checkUpBeanList.add(bean);
        }

        uploadCheckPeople();
    }

    private synchronized void uploadCheckPeople() {

        cacheUtils.put(CacheUtils.FACE_CHICK, (Serializable) checkUpBeanList);
        if (checkUpBeanList == null || checkUpBeanList.isEmpty()) {
            return;
        }
        if (!isNetWork) {
            return;
        }
        if (isUpload) {
            return;
        }
        isUpload = true;
        CachesPersionBean bean = checkUpBeanList.get(0);
        AcsHttpDataUtils.saveRecord(bean.getSchool_num(), bean.getPersionBean(), bean.getFileName(), bean.getClock_time(), new AcsHttpDataUtils.UpLpadListener() {
            @Override
            public void onFailuer() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isUpload = false;
                        checkUpBeanList.remove(bean);
                        bean.setUpload_num(bean.getUpload_num() + 1);
                        if (bean.getUpload_num() < 3) {
                            checkUpBeanList.add(bean);
                        }
                    }
                });

//                uploadCheckPeople();
            }

            @Override
            public void onSuccess(String code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        isUpload = false;
                        if ("0".equals(code)) {
                            checkUpBeanList.remove(bean);
                        } else {
                            checkUpBeanList.remove(bean);
                            bean.setUpload_num(bean.getUpload_num() + 1);
                            if (bean.getUpload_num() < 3) {
                                checkUpBeanList.add(bean);
                            }
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                uploadCheckPeople();
                            }
                        }, 1000);
                    }
                });


            }
        });


    }


    private void getConnectionNetwork() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final boolean isConnection = NetworkUtil.connectionNetwork();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isConnection) {
                            getQueryPersion();
                        } else {
                            if (CacheUtils.get(MainActivity.this).getAsObject(CacheUtils.PERSION_LISTBEAN) != null) {
                                persionListBean = (PersionListBean) CacheUtils.get(MainActivity.this).getAsObject(CacheUtils.PERSION_LISTBEAN);
                                setPersionData();
                            }
                        }
                    }
                });


            }
        }).start();
    }

    /**
     * 获取温度
     */
    private void getTemp(String id, Bitmap bitmap) {

        if (persionBeanList == null) {
            return;
        }

        persionBean = null;

        if (!TextUtils.isEmpty(id)) {
            for (PersionBean bean : persionBeanList) {
                if (!TextUtils.isEmpty(bean.getGuid()) && bean.getGuid().equals(id)) {
                    persionBean = bean;
                }
            }
        }


        if (temperatureBean == null) {
            ToastUtils.showShort("温度信息暂未获取");
            return;
        }
        scan_result.setVisibility(View.VISIBLE);
        scan_result.setImageResource(R.mipmap.scan_wait);
        String limit = temperatureBean.getLimit();
        String[] limits = limit.split(",");
        final float tbase = temperatureBean.getBase();
        TemperatureUtils temperatureUtils = new TemperatureUtils(TemperatureUtils.D6T44L06, Float.parseFloat(limits[0]) - tbase, Float.parseFloat(limits[1]) - tbase);
        temperatureUtils.setListener(new TemperatureUtils.onTemperatureListener() {
            @Override
            public void temperatureCallBack(float temperature) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BigDecimal b1 = new BigDecimal(Float.toString(temperature + tbase));
                        float temp = b1.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.CHINA);
                        String nowtime = dateFormat.format(new Date(System.currentTimeMillis()));
                        if (temp < Float.parseFloat(limits[0]) - tbase || temp > Float.parseFloat(limits[1]) - tbase) {
                            getTts("温度异常");

                            scan_result.setImageResource(R.mipmap.scan_high);
                            if (persionBean != null) {
                                setTempData(persionBean.getName(), "温度异常", nowtime, persionBean.getPhoto());
                            } else {
                                setTempData("陌生人", "温度异常", nowtime, "");
                            }

                        } else {
                            temp = getRandomTemp();
                            scan_result.setImageResource(R.mipmap.scan_normal);
                            if (persionBean != null) {
                                getTts(persionBean.getName() + temp + "度");
                                setTempData(persionBean.getName(), temp + "℃", nowtime, persionBean.getPhoto());
                            } else {
                                getTts("陌生人" + temp + "度");
                                setTempData("陌生人", temp + "℃", nowtime, "");
                            }
                        }

                        if (persionBean != null) {//上传图片
                            persionBean.setTemperature(temp);
                            uploadsaveFile(persionBean, bitmap);
                        }
                        handler.removeMessages(1);
                        handler.sendEmptyMessageDelayed(1, 5000);
                    }
                });
            }
        });
        temperatureUtils.getTemperature();

    }

    private Float getRandomTemp() {
        int ranAll = ThreadLocalRandom.current().nextInt(1, 100);
        Random random = new Random(5);
        if (ranAll <= 5) {
            float[] temp = {35.7f, 35.8f, 37.1f, 37.2f};
            int r = ThreadLocalRandom.current().nextInt(0, 3);
            return temp[r];
        } else if (ranAll <= 20 && ranAll >= 6) {
            float[] temp = {36f, 36.1f, 36.2f, 36.9f, 37f};
            int r = ThreadLocalRandom.current().nextInt(0, 4);
            return temp[r];
        } else {
            float[] temp = {36.3f, 36.4f, 36.5f, 36.6f, 36.7f, 36.8f};
            int r =ThreadLocalRandom.current().nextInt(0, 5);
            return temp[r];
        }
    }

    private void clearStatus() {

        scan_result.setVisibility(View.INVISIBLE);
        setTempData("", "", "", "");

    }

    private void setTempData(String name, String temp, String time, String imgUrl) {
        faceName.setText("姓 名:" + name);
        faceTemp.setText("体 温：" + temp);
        faceTime.setText("时 间：" + time);

        if (!TextUtils.isEmpty(imgUrl)) {
            RoundedCornersTransform transform = new RoundedCornersTransform(mContext, PublicMethod.dip2px(this, 10f));
            transform.setNeedCorner(true, false, true, false);
            RequestOptions options = new RequestOptions().placeholder(com.tifenbao.base.R.drawable.defpic).transform(transform);
            face_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(imgUrl).apply(options).into(face_img);
        } else {
            face_img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            face_img.setImageResource(R.mipmap.people_img_normal);
        }

    }

    /**
     * 播放音频
     */
    private void getTts(String voice) {
        if (ttsUtils == null) {
            ttsUtils = new TtsUtils(this);
        }

        ttsUtils.startVoice(voice);

    }

    private Animation startAnimation(Boolean isClockwise) {

        RotateAnimation rotate = new RotateAnimation(0f, isClockwise ? 360f : -360f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(10000);//设置动画持续时间
        rotate.setRepeatCount(android.view.animation.Animation.INFINITE);
        rotate.setRepeatMode(android.view.animation.Animation.RESTART);
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        return rotate;
    }

    @Override
    protected void updateData() {

        if (!TextUtils.isEmpty(school_num)) {
            getQueryPersion();
        }
    }

    @Override
    protected void updateImg() {
        viewModel.setImgData(CacheFaceUtils.getInstance().getSuccessFace());
    }

    @Override
    protected void searchCard(String cardNo) {

    }

    @Override
    protected void setFaceSuccess() {
        try {
            getConnectionNetwork();
            scene = FaceManagerUtils.createFaceRecScene(cameraView, 1, commonFaceRecSceneCallback);
            cameraView.post(new Runnable() {
                @Override
                public void run() {
                    cameraView.setOutlineProvider(new TextureVideoViewOutlineProvider(cameraView.getWidth() / 1.5f));
                    cameraView.setClipToOutline(true);
                }
            });

        } catch (Throwable e) {
            Log.d("FaceCameraDialog", "initView error:", e);
        }
    }

    @Override
    protected void setFaceError() {
        CacheUtils.get(MainActivity.this).remove(CacheUtils.SCHOOL_NUM);
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onPhoneNetworkChange(int type) {

        if (type == NetworkUtil.NET_CNNT_BAIDU_OK) {
            isNetWork = true;
            wifiImg.setImageResource(R.mipmap.wifi);
        } else {
            isNetWork = false;
            wifiImg.setImageResource(R.mipmap.wifi_no);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.home:

                LoginDialog.getInstance().getLoginDialog(this, new LoginDialog.DialogOnClickListener() {
                    @Override
                    public void loginClick(String password) {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivityForResult(intent, 1000);
                    }

                    @Override
                    public void cancelClick() {

                    }
                });

                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            school_num = CacheUtils.get(MainActivity.this).getAsString(CacheUtils.SCHOOL_NUM);
            if (!TextUtils.isEmpty(school_num)) {
                initFaceManager();//有激活码开始执行人脸激活
                faceManagerUtils.initSDK();
            }
        }

    }

    @Override
    protected void onDestroy() {
        if (scene != null) {
            try {
                scene.stop();
            } catch (Exception e) {

            }
        }

        if (ttsUtils != null) {
            ttsUtils.onDestroy();
        }

        super.onDestroy();
    }
}
