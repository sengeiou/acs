package com.tifenbao.newfacemanager;

/**
 * 设备硬件适配器
 */
public interface DeviceAdapter {

    /**
     * 获取普通摄像头ID
     */
    int getPreviewCameraId();

    int getPreviewCameraOrientation();

    /**
     * 获取红外摄像头ID
     */
    int getInfraredCameraId();

    /**
     * 获取普通摄像头帧图片旋转角度
     */
    int getPreviewCameraRotateDegree();

    /**
     * 获取红外摄像头帧图片旋转角度
     */
    int getInfraredCameraRotateDegree();

    /**
     * 普通摄像头帧数据是否水平翻转
     */
    boolean isPreviewCameraFlipX();

    /**
     * 普通摄像头帧数据是否竖直翻转
     */
    boolean isPreviewCameraFlipY();

    /**
     * 红外摄像头帧数据是否水平翻转
     */
    boolean isInfraredCameraFlipX();

    /**
     * 红外摄像头帧数据是否竖直翻转
     */
    boolean isInfraredCameraFlipY();

    /**
     * 红外摄像头相对普通摄像头水平方向偏移距离（像素）
     */
    int getInfraredCameraOffsetX();

    /**
     * 红外摄像头相对普通摄像头竖直方向偏移距离（像素）
     */
    int getInfraredCameraOffsetY();

    /**
     * 摄像头焦距
     */
    int getCameraZoom();

    /**
     * 摄像头预览宽度
     */
    int getPreviewWidth();

    /**
     * 摄像头预览高度
     */
    int getPreviewHeight();
//
//    /**
//     * 门开闭驱动器
//     */
//    EntranceDriver getEntranceDriver();
//
//    /**
//     * LED灯驱动器
//     */
//    LedDriver getLedDriver();
//
//    /**
//     * 注册唤醒休眠事件监听器
//     *
//     * @param listener 监听器
//     * @return true-注册成功 false-注册失败
//     */
//    boolean setAwakeSleepListener(AwakeSleepListener listener);
}
