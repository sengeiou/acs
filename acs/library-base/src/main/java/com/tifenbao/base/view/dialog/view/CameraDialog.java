package com.tifenbao.base.view.dialog.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SearchView.OnCloseListener;
import android.widget.TextView;
import android.widget.Toast;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.upload.UploadBaseBean;
import com.tifenbao.base.http.login.LoginHttpDataUtils;
import com.tifenbao.base.util.BitmapUtils;
import com.tifenbao.base.util.GlideUtils;
import com.tifenbao.mvvmhabit.utils.ToastUtils;
import com.xinshi.android.face.camera.FaceCamera;
import com.xinshi.android.xsfacesdk.helper.XsFaceSDKHelper;


public class CameraDialog extends Dialog {

    private TextureView textureview;
    private Camera camera;
    private boolean isPreview;

    private TextView nameTxt;
    private TextView statusTxt;
    private TextView timeTxt;
    private TextView cardIdTxt;
    private ImageView img_photo;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    private String className;
    private String cardId;//卡号
    private String name;//姓名
    private String time;//打卡时间
    private String starus;//打卡状态 ok ：正常入校，late：迟到，leave：离校
    private String imgurl;//头像地址
    private int result;
    private String classCamera;
    private boolean isTakePhoto=false;

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub

            if (msg.what == 0) {

                if (camera != null && isPreview) {
                    camera.setPreviewCallback(new Camera.PreviewCallback() {
                        @Override
                        public void onPreviewFrame(byte[] bytes, Camera camera) {
                            sendImg(bytes);
                        }
                    });
                }
            } else {
                dismiss();
            }


            return false;
        }
    });

    public CameraDialog(Context context, int theme, String cardId, String className, String name, String time, String starus, String imgurl, String classCamera) {
        super(context, theme);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mContext = context;
        this.cardId = cardId;
        this.className = className;
        this.name = name;
        this.time = time;
        this.starus = starus;
        this.imgurl = imgurl;
        this.classCamera = classCamera;
    }

    public CameraDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CameraDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected CameraDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_checkin_attendance);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        cardIdTxt = (TextView) findViewById(R.id.card_number);
        statusTxt = (TextView) findViewById(R.id.starus);
        timeTxt = (TextView) findViewById(R.id.time);
        nameTxt = (TextView) findViewById(R.id.name);
        img_photo = (ImageView) findViewById(R.id.img_photo);
        cardIdTxt.setText("上课地点:" + className);
        nameTxt.setText("姓名:" + name);
        timeTxt.setText("打卡时间:" + time);

        if (!TextUtils.isEmpty(starus)) {

            if (starus.equals("late")) {
                statusTxt.setBackgroundResource(R.drawable.bg_shape_translucent_radius80);
                statusTxt.setText("考勤状态:迟到");
            } else if (starus.equals("ok")) {
                statusTxt.setText("考勤状态:正常入校");
            } else {
                statusTxt.setText("考勤状态:离校");
            }

        }

        if (!TextUtils.isEmpty(imgurl)) {
            GlideUtils.setImageUrl(mContext, imgurl, img_photo);
        }

        if ("1".equals(classCamera)) {
            findViewById(R.id.nocamera).setVisibility(View.GONE);
            textureview = (TextureView) findViewById(R.id.textureview);

            textureview.setVisibility(View.VISIBLE);
//                    faceBoxView.setVisibility(View.GONE);
            textureview.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                @Override
                public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, int i, int i1) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestPermission(surfaceTexture);
                        }
                    }).start();


                }

                @Override
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

                }

                @Override
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {

                    try {
                        if (camera != null) {
                            if (isPreview) {//如果正在预览
                                isPreview = false;
                                camera.stopPreview();
                                camera.setPreviewCallback(null);
                                camera.release();
                                camera = null;
                            }
                        }
                    } catch (Exception e) {

                    }

                    return false;
                }

                @Override
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

                }
            });

            textureview.setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.surface).setVisibility(View.GONE);
            findViewById(R.id.nocamera).setVisibility(View.VISIBLE);

            handler.sendEmptyMessageDelayed(1, 5000);
        }


    }


    private void sendImg(byte[] bytes){

        if (!isTakePhoto) {

            isTakePhoto = true;
            /* onPictureTaken传入的第一个参数即为相片的byte */
            Camera.Parameters parameters = camera.getParameters();
            int frameWidth = parameters.getPreviewSize().width;
            int frameHeight = parameters.getPreviewSize().height;
            final Bitmap bm = BitmapUtils.getYuvBitmap(result, bytes, frameWidth, frameHeight);
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap img;
                        if (result == 180 || result == 0) {
                            img = BitmapUtils.MirrorImage(BitmapUtils.comp(bm));
                        } else {
                            img = BitmapUtils.comp(bm);
                        }
                        LoginHttpDataUtils.sendImg(cardId, BitmapUtils.saveBitmapFile(mContext,img), new LoginHttpDataUtils.UpLpadListener() {

                            @Override
                            public void onFailuer() {
                                dismiss();
                            }

                            @Override
                            public void onSuccess(UploadBaseBean.UploadBean uploadBean) {
                                dismiss();
                            }
                        });
                    }
                }).start();


            } catch (Exception e) {
                Log.e("cameraException", e.getMessage());
            }

        }


    }

    /***
     * 申请权限
     */
    void RequestPermission(SurfaceTexture texture) {
        if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{android.Manifest.permission.CAMERA}, 1);
            Log.d("test", "RequestPermission");
        } else {
            initCamera(texture);
        }
    }

    public void initCamera(SurfaceTexture texture) {

        try {
//            camera = Camera.open(1);//打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera
//            camera = getCameraInstance();
            Camera.CameraInfo info = new Camera.CameraInfo();
            FaceCamera faceCamera = XsFaceSDKHelper.getEnvConfig().getVisCamera();
            if (faceCamera != null && faceCamera.getCameraParams() != null) {
                camera = Camera.open(faceCamera.getCameraParams().getCameraId());
            } else {
                camera = Camera.open();
            }
            if (camera == null) {
                ToastUtils.showShort("未检测到摄像头", Toast.LENGTH_LONG);
                return;
            }

            if (null != camera.getParameters()) {
                Camera.Parameters parameters = camera.getParameters();//得到摄像头的参数
//						    parameters.setPreviewSize(surfaceView.getWidth(),surfaceView.getHeight());//设置预览照片的大小
                parameters.setRotation(0);
                parameters.setPreviewFrameRate(3);//设置每秒3帧
                parameters.setPictureFormat(PixelFormat.JPEG);//设置照片的格式
                parameters.setJpegQuality(85);//设置照片的质量
                camera.setParameters(parameters);
//						    camera.setPreviewDisplay(holder);\
            }
            camera.setPreviewTexture(texture);//通过Textureview显示取景画面
            if (faceCamera != null && faceCamera.getCameraParams() != null) {
                Camera.getCameraInfo(faceCamera.getCameraParams().getCameraId(), info);
            }
            if (info != null) {
                int rotation = ((Activity) mContext).getWindowManager().getDefaultDisplay()
                        .getRotation();
                int degrees = 0;

                switch (rotation) {
                    case Surface.ROTATION_0:
                        degrees = 0;
                        break;
                    case Surface.ROTATION_90:
                        degrees = 90;
                        break;
                    case Surface.ROTATION_180:
                        degrees = 180;
                        break;
                    case Surface.ROTATION_270:
                        degrees = 270;
                        break;
                }
                if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    result = (info.orientation + degrees) % 360;
                    result = (360 - result) % 360; // compensate the mirror
                } else {
                    // back-facing
                    result = (info.orientation - degrees + 360) % 360;
                }
                camera.setDisplayOrientation(result);
            }

            camera.startPreview();//开始预览
            isPreview = true;//设置是否预览参数为真

            handler.sendEmptyMessageDelayed(0, 2000);

        } catch (Exception e) {
            Log.i("cameraException", e.toString());
        }


    }

}

