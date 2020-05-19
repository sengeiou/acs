package com.tifenbao.base.view.dialog.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.tfb.temperature.utils.TemperatureUtils;
import com.tifenbao.base.R;
import com.tifenbao.base.bean.CacheIndextBean;
import com.tifenbao.base.bean.index.StudentBean;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.util.CardDateUtils;
import com.tifenbao.base.util.GlideUtils;
import com.tifenbao.base.view.ScalableImageView;
import com.tifenbao.base.view.dialog.itemview.SignFaceItemView;
import com.tifenbao.base.view.recycleview.MultiItemTypeAdapter;
import com.tifenbao.base.view.recycleview.RecyclerViewAdapter;
import com.tifenbao.newfacemanager.FaceManagerUtils;
import com.xinshi.android.face.data.SearchedPerson;
import com.xinshi.android.face.view.SingleCameraView;
import com.xinshi.android.xsfacesdk.scene.CommonFaceRecScene;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于人脸识别考情弹窗
 * <p>
 * mar
 * 2019/8/24
 */
public class FaceCardCameraDialog extends BaseDialog {

    private List<StudentBean> studentBeanList;
    private RecyclerViewAdapter signstudentAdapter;

    private ScalableImageView photo;
    private TextView studentname;
    private TextView classname;
    private TextView status_time;
    private TextView status;
    private TextView temperatureText;
    private TextView temperatureHigh;

    private String className;

    private CheckInFaceListener checkInFaceListener;

    private long checkTime = 0;

    private String nowFaceId = "";

    private SingleCameraView cameraView;
    private CommonFaceRecScene scene;
    private CommonFaceRecScene.CommonFaceRecSceneCallback commonFaceRecSceneCallback;

    public Dialog geFaceCardCameraDialog(final Context context, List<StudentBean> list, String className, int liveness, boolean isAuto, CheckInFaceListener checkInFaceListener) {

        this.checkInFaceListener = checkInFaceListener;
        this.className = className;
        this.context = context;

        View dialogview = View.inflate(context, R.layout.dialog_facecardcamera, null);

        photo = dialogview.findViewById(R.id.student_photo);
        photo.setRoundConner(80);
        classname = dialogview.findViewById(R.id.classname);
        status_time = dialogview.findViewById(R.id.time);
        status = dialogview.findViewById(R.id.status);
        studentname = dialogview.findViewById(R.id.student_name);
        temperatureText = dialogview.findViewById(R.id.temperature);
        temperatureHigh = dialogview.findViewById(R.id.temperature_high);

        if (CacheIndextBean.getInstance().getIndexBean().getTemperature_mode() == 1) {
            temperatureText.setVisibility(View.VISIBLE);
        } else {
            temperatureText.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = dialogview.findViewById(R.id.sign_recycleview);

        studentBeanList = new ArrayList<>();
        if (list != null) {
            studentBeanList.addAll(list);
        }
        clearCheckIn();//每次都要清一下状态
        MultiItemTypeAdapter signstudentItemAdapter = new MultiItemTypeAdapter(context, studentBeanList);

        signstudentItemAdapter.addItemViewDelegate(new SignFaceItemView(context));

        signstudentAdapter = new RecyclerViewAdapter(signstudentItemAdapter);
        recyclerView.setAdapter(signstudentAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 6);
        recyclerView.setLayoutManager(gridLayoutManager);

        cameraView = (SingleCameraView) dialogview.findViewById(R.id.camera_view);

        commonFaceRecSceneCallback = new CommonFaceRecScene.CommonFaceRecSceneCallback() {
            @Override
            public void onFaceRecComplete(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, final SearchedPerson searchedPerson, List<SearchedPerson> list) {

                if (searchedPerson.getPerson().getPersonData() != null) {

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //更新搜索缓存
                            long systemTime = System.currentTimeMillis();

                            if (!TextUtils.isEmpty(String.valueOf(searchedPerson.getPerson().getPersonCode())) && (systemTime - checkTime > BaseConstant.BASE_FACE_COUNTDOWN_SECOND || !String.valueOf(searchedPerson.getPerson().getPersonCode()).equals(nowFaceId))) {
                                checkTime = systemTime;
                                nowFaceId = String.valueOf(searchedPerson.getPerson().getPersonCode());
//                                setCheckIn(checkTime, String.valueOf(searchedPerson.getPerson().getPersonCode()));
                                if (CacheIndextBean.getInstance().getIndexBean().getTemperature_mode() == 1) {
                                    setTemperature(checkTime, String.valueOf(searchedPerson.getPerson().getPersonCode()));
                                } else {
                                    setCheckIn(checkTime, String.valueOf(searchedPerson.getPerson().getPersonCode()), 0);
                                }

                            }

                        }
                    });
                }

            }

            @Override
            public void onRecStranger(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, SearchedPerson searchedPerson) {

            }

            @Override
            public void onLivenessAttack(CommonFaceRecScene.FaceRecSceneData faceRecSceneData, SearchedPerson searchedPerson, List<SearchedPerson> list) {

            }
        };

        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        scene = FaceManagerUtils.createFaceRecScene(cameraView,liveness, commonFaceRecSceneCallback);
                    } catch (Exception e) {

                    }
                }

            }).start();
        } catch (Throwable e) {
            Log.d("FaceCameraDialog", "initView error:", e);
        }


        dialog = new Dialog(context, R.style.loaddialog);


        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(dialogview);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);
        dialog.show();

        if (isAuto) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkInFaceListener.cancel();
                    cancel();
                }
            }, 10000);
        }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (scene != null) {
                    try {
                        scene.stop();
                    } catch (Exception e) {

                    }

                }
            }
        });


        return dialog;
    }

    private void clearCheckIn() {
        for (StudentBean studentBean : studentBeanList) {
            studentBean.setStatus(CardDateUtils.NOT_ATTENDANCE_STATUS);
        }
    }

    private synchronized void setTemperature(long checkTime, String id) {

        String limit=CacheIndextBean.getInstance().getIndexBean().getTemperature_limit();
        String[] limits=limit.split(",");
        float tbase=CacheIndextBean.getInstance().getIndexBean().getTemperature_base();
        TemperatureUtils temperatureUtils = new TemperatureUtils(TemperatureUtils.D6T44L06,Float.parseFloat(limits[0])-tbase,Float.parseFloat(limits[1])-tbase);
        temperatureUtils.setListener(new TemperatureUtils.onTemperatureListener() {
            @Override
            public void temperatureCallBack(float temperature) {
                BigDecimal b1 = new BigDecimal(Float.toString(temperature + tbase));
                setCheckIn(checkTime, id, b1.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
            }
        });
        temperatureUtils.getTemperature();
    }

    private synchronized void setCheckIn(long checkTime, String id, float temperature) {

        for (StudentBean studentBean : studentBeanList) {

            if (id.equals(studentBean.getId())) {

                studentBean.setStatus(CardDateUtils.checkStudentStatus(checkTime));
                studentBean.setTemperature(temperature);
                Date d = new Date(checkTime);
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                setStudentStatus(studentBean, sf.format(d), temperature);

                checkInFaceListener.checkInface(studentBean);

                break;
            }
        }
        signstudentAdapter.notifyDataSetChanged();

    }

    private void setStudentStatus(StudentBean studentBean, String time, float temperature) {

        GlideUtils.setImageUrl(context, studentBean.getPhoto(), photo);
        studentname.setText(studentBean.getStudent_name());
        classname.setText(Html.fromHtml("班级：<font color='#1f5cb9'>" + className + "</font>"));
        status_time.setText(Html.fromHtml("时间：<font color='#1f5cb9'>" + time + "</font>"));
        String statusText = "";
        if (studentBean.getStatus() == CardDateUtils.STATUS_COME) {//到了
            statusText = "状态：<font color='#1f5cb9'>打卡成功</font>";
        } else if (studentBean.getStatus() == CardDateUtils.STATUS_LATE) {//迟到
            statusText = "状态：<font color='#FF3119'>迟到</font>";
        } else if (studentBean.getStatus() == CardDateUtils.STATUS_LEAVE) {//离校
            statusText = "状态：<font color='#FF3119'>离校</font>";
        } else {//未到
            statusText = "状态：<font color='#FF3119'>未到</font>";
        }
        status.setText(Html.fromHtml(statusText));
        String temperHtml = "";
        temperatureHigh.setVisibility(View.GONE);
        temperatureText.setVisibility(View.GONE);
        if (temperature > 0) {
            if (temperature > CacheIndextBean.getInstance().getIndexBean().getTemperature_warning()) {
                temperHtml = "体温：<font color='#FF3119'>" + temperature + "℃</font>";
                temperatureHigh.setVisibility(View.VISIBLE);
            } else {
                temperHtml = "体温：<font color='#1f5cb9'>" + temperature + "℃</font>";
            }
            temperatureText.setVisibility(View.VISIBLE);
            temperatureText.setText(Html.fromHtml(temperHtml));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clearStudentStatus();
            }
        }, 5000);
    }

    private void clearStudentStatus() {
        GlideUtils.setImageUrl(context, "", photo);
        classname.setText("班级：");
        status_time.setText("时间：");
        status.setText("状态：");
        studentname.setText("");
        temperatureHigh.setVisibility(View.GONE);
        if (CacheIndextBean.getInstance().getIndexBean().getTemperature_mode() == 1) {
            temperatureText.setText("体温：");
        }

    }

    public void setStudentBeanList(List<StudentBean> studentBeanList) {
        if (this.studentBeanList != null) {
            this.studentBeanList.clear();
            this.studentBeanList.addAll(studentBeanList);
        } else {
            this.studentBeanList = studentBeanList;
        }

    }

    public interface CheckInFaceListener {
        void checkInface(StudentBean studentBean);

        void cancel();
    }

}
