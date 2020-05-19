package com.tifenbao.newfacemanager;

import android.content.Context;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.xinshi.android.face.camera.AndroidCameraSource1;
import com.xinshi.android.face.camera.CameraParams;
import com.xinshi.android.face.camera.FaceCamera;
import com.xinshi.android.face.camera.FaceCameraView;
import com.xinshi.android.face.camera.Size;
import com.xinshi.android.face.jni.Tool;
import com.xinshi.android.xsfacesdk.XsSdkEnvConfig;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 旧版适配器配置
 */
public class OldDeviceConfig {
    int previewCameraId = 0;
    int previewCameraOrientation = -1;
    int infraredCameraId = 1;
    int previewCameraRotateDegree = 90;
    int infraredCameraRotateDegree = 90;
    int infraredCameraOffsetX = 0;
    int infraredCameraOffsetY = 0;
    boolean previewCameraFlipX = true;
    boolean previewCameraFlipY = false;
    boolean infraredCameraFlipX = true;
    boolean infraredCameraFlipY = false;
    int previewWidth = 480;
    int previewHeight = 640;
    int cameraZoom = 0;
    String entranceDriverName = "default";
    String ledDriverName = "default";
    String awakeSleepSensorName = "default";

    public int getPreviewCameraId() {
        return previewCameraId;
    }

    public void setPreviewCameraId(int previewCameraId) {
        this.previewCameraId = previewCameraId;
    }

    public int getPreviewCameraOrientation() {
        return previewCameraOrientation;
    }

    public void setPreviewCameraOrientation(int previewCameraOrientation) {
        this.previewCameraOrientation = previewCameraOrientation;
    }

    public int getInfraredCameraId() {
        return infraredCameraId;
    }

    public void setInfraredCameraId(int infraredCameraId) {
        this.infraredCameraId = infraredCameraId;
    }

    public int getPreviewCameraRotateDegree() {
        return previewCameraRotateDegree;
    }

    public void setPreviewCameraRotateDegree(int previewCameraRotateDegree) {
        this.previewCameraRotateDegree = previewCameraRotateDegree;
    }

    public int getInfraredCameraRotateDegree() {
        return infraredCameraRotateDegree;
    }

    public void setInfraredCameraRotateDegree(int infraredCameraRotateDegree) {
        this.infraredCameraRotateDegree = infraredCameraRotateDegree;
    }

    public int getInfraredCameraOffsetX() {
        return infraredCameraOffsetX;
    }

    public void setInfraredCameraOffsetX(int infraredCameraOffsetX) {
        this.infraredCameraOffsetX = infraredCameraOffsetX;
    }

    public int getInfraredCameraOffsetY() {
        return infraredCameraOffsetY;
    }

    public void setInfraredCameraOffsetY(int infraredCameraOffsetY) {
        this.infraredCameraOffsetY = infraredCameraOffsetY;
    }

    public boolean isPreviewCameraFlipX() {
        return previewCameraFlipX;
    }

    public void setPreviewCameraFlipX(boolean previewCameraFlipX) {
        this.previewCameraFlipX = previewCameraFlipX;
    }

    public boolean isPreviewCameraFlipY() {
        return previewCameraFlipY;
    }

    public void setPreviewCameraFlipY(boolean previewCameraFlipY) {
        this.previewCameraFlipY = previewCameraFlipY;
    }

    public boolean isInfraredCameraFlipX() {
        return infraredCameraFlipX;
    }

    public void setInfraredCameraFlipX(boolean infraredCameraFlipX) {
        this.infraredCameraFlipX = infraredCameraFlipX;
    }

    public boolean isInfraredCameraFlipY() {
        return infraredCameraFlipY;
    }

    public void setInfraredCameraFlipY(boolean infraredCameraFlipY) {
        this.infraredCameraFlipY = infraredCameraFlipY;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public int getCameraZoom() {
        return cameraZoom;
    }

    public void setCameraZoom(int cameraZoom) {
        this.cameraZoom = cameraZoom;
    }

    public String getEntranceDriverName() {
        return entranceDriverName;
    }

    public void setEntranceDriverName(String entranceDriverName) {
        this.entranceDriverName = entranceDriverName;
    }

    public String getLedDriverName() {
        return ledDriverName;
    }

    public void setLedDriverName(String ledDriverName) {
        this.ledDriverName = ledDriverName;
    }

    public String getAwakeSleepSensorName() {
        return awakeSleepSensorName;
    }

    public void setAwakeSleepSensorName(String awakeSleepSensorName) {
        this.awakeSleepSensorName = awakeSleepSensorName;
    }

    private static int getDisplayOrientation(Context context, int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int rotation = display.getRotation();
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

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    /**
     * 将旧版适配器装入新版，并保存
     *
     * @param envConfig
     * @throws IOException
     * @throws JSONException
     */
    public void copyConfigToEnvConfig(Context context, XsSdkEnvConfig envConfig) throws IOException, JSONException {
        List<FaceCamera> cameraList = envConfig.getCameras();
        cameraList.clear();
        int previewCameraOrientation = getPreviewCameraOrientation();
        int pictureCameraOrientation = getPreviewCameraRotateDegree();
        if (previewCameraOrientation == -1) {
            previewCameraOrientation = getDisplayOrientation(context, getPreviewCameraId());
        }
        CameraParams params = new CameraParams(getPreviewCameraId(), 640, new Size(getPreviewWidth(), getPreviewHeight()),
                previewCameraOrientation, pictureCameraOrientation, isPreviewCameraFlipX(), CameraParams.CameraType.VIS_CAMERA);
        cameraList.add(new FaceCamera(new AndroidCameraSource1(getPreviewCameraId()), params));
        envConfig.setFaceCameraViewMode(FaceCameraView.ViewMode.TEXTURE_VIEW);
        envConfig.save();
    }

    public static OldDeviceConfig loadFromString(String jsonString) throws IOException {
        return JSON.parseObject(jsonString, OldDeviceConfig.class);
    }

    public static OldDeviceConfig loadFromFile(String fileName) throws IOException {
        String jsonString = new String(Tool.loadData(new File(fileName)), "utf-8");
        return JSON.parseObject(jsonString, OldDeviceConfig.class);
    }
}
