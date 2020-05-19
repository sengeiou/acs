package com.tifenbao.newfacemanager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.tifenbao.facemanager.R;
import com.xinshi.android.face.camera.FaceCamera;
import com.xinshi.android.face.camera.FaceCameraView;
import com.xinshi.android.face.data.FaceData;
import com.xinshi.android.face.data.FaceDetectData;
import com.xinshi.android.face.data.LivenessDetectMode;
import com.xinshi.android.face.processor.BaseFaceRecProcessor;
import com.xinshi.android.xsfacesdk.helper.XsFaceSDKHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tifenbao.newfacemanager.NativeFaceConstant.CAMERA_SEARCH_SUCCESS;

/**
 * 本地图片人脸识别
 */
public class NativeFaceCameraDialog extends Dialog {

    private FaceBoxView faceBoxView;

    private FaceRecView cameraView;

    private Context mContext;

    private int autoclose;

    private List<String> faceIdList;

    private NativeFaceCameraDialogCallback callback;

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub

            try {
                dismiss();
            } catch (Exception e) {
            }

            return false;
        }
    });

    public NativeFaceCameraDialog(Context context, int autoclose, int theme, NativeFaceCameraDialogCallback callback) {
        super(context, theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.mContext = context;
        this.callback = callback;
        this.autoclose = autoclose;

        faceIdList = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facecamera_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {

        //人脸框绘画自定义控件
        faceBoxView = findViewById(R.id.face_box_view);
        faceBoxView.setTranslationZ(1);
        //将人脸框绘制控件放置到最上层
        faceBoxView.bringToFront();


        cameraView = (FaceRecView) findViewById(R.id.face_rec_view);

        try {
            FaceCamera faceCamera = XsFaceSDKHelper.getEnvConfig().getVisCamera();
            cameraView.setCamera(faceCamera);
            faceBoxView.setPreviewViewSize(faceCamera.getCameraParams().getPreviewViewSize());
            cameraView.setViewMode(FaceCameraView.ViewMode.TEXTURE_VIEW);
            cameraView.setFaceDataView(faceBoxView);
            BaseFaceRecProcessor.FaceRecConfig config = new BaseFaceRecProcessor.FaceRecConfig(
                    new BaseFaceRecProcessor.FaceRecStep[]{BaseFaceRecProcessor.extractFeatureStep, BaseFaceRecProcessor.faceSearchStep});
            config.livenessDetectMode = LivenessDetectMode.NONE;
            config.minFaceSize = 110;
            XsFaceSDKHelper.createFaceRecProcessor(XsFaceSDKHelper.getEnvConfig().getVisCamera()
                    , null, config, new BaseFaceRecProcessor.FaceRecCallback() {
                        @Override
                        public void onFaceRecCompleted(FaceDetectData faceDetectData, FaceData faceData, final BaseFaceRecProcessor.FaceTrackData faceTrackData) {
                            if (faceTrackData.searchTimes.get() > 0) {
                                if (faceTrackData.searchPass && faceTrackData.searchedPerson != null) {
                                    ((Activity) mContext).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //更新搜索缓存
                                            if (!faceIdList.contains(String.valueOf(faceTrackData.searchedPerson.getPerson().getPersonCode()))) {
                                                faceIdList.add(String.valueOf(faceTrackData.searchedPerson.getPerson().getPersonCode()));
                                                if (faceIdList.size() > 10) {//每次识别成功加载到数组里面，数组超过10时移除第一个
                                                    faceIdList.remove(0);
                                                }
//                                handler.removeMessages(1);
                                                callback.cameraCallback(CAMERA_SEARCH_SUCCESS, String.valueOf(faceTrackData.searchedPerson.getPerson().getPersonCode()));
                                            }
                                        }
                                    });
                                } else {
//                                        callback.cameraCallback(CAMERA_SEARCH_ERROR, dialogStyle, "onDetectResult: 搜索失败，标记为陌生人: track_id: " + faceData.getTrackId());
                                }
                            }
                        }

                        @Override
                        public void onFaceDetected(FaceDetectData faceDetectData) {
                            faceBoxView.setFaceDetectData(faceDetectData);
                        }

                        @Override
                        public void onLivenessDetected(FaceDetectData faceDetectData) {
                        }

                        @Override
                        public void onFaceChanged(FaceDetectData faceDetectData, List<FaceData> list, List<FaceData> list1) {
                            faceBoxView.setFaceDetectData(faceDetectData);
                        }
                    });
        } catch (Throwable e) {
            Log.d("FaceCameraDialog", "initView error:", e);
        }

        (findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

        if (autoclose != 0) {
            handler.sendEmptyMessageDelayed(1, autoclose);
        }
    }

}

