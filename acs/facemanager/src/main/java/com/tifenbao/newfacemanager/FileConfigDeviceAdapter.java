package com.tifenbao.newfacemanager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileConfigDeviceAdapter implements DeviceAdapter {


    private final DeviceConfig deviceConfig;

    public FileConfigDeviceAdapter(File configFile) throws Exception {
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            deviceConfig = new DeviceConfig();
            String content = JSON.toJSONString(deviceConfig, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
            writeTextFile(configFile, content);
        } else {
            if (configFile.isDirectory()) {
                throw new Exception("config file is directory");
            }
            String content = readTextFile(configFile);
            deviceConfig = JSON.parseObject(content, DeviceConfig.class);
        }
    }

    public static String readTextFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Throwable var2 = null;

        try {
            StringBuilder result = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                result.append(line);
            }

            String var5 = result.toString();
            return var5;
        } catch (Throwable var14) {
            var2 = var14;
            throw var14;
        } finally {
            if (reader != null) {
                if (var2 != null) {
                    try {
                        reader.close();
                    } catch (Throwable var13) {
                        var2.addSuppressed(var13);
                    }
                } else {
                    reader.close();
                }
            }

        }
    }

    public static void writeTextFile(File file, String content) throws Exception {
        if (file.isDirectory()) {
            throw new Exception("file is directory");
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Throwable var3 = null;

            try {
                writer.append(content);
                writer.flush();
            } catch (Throwable var12) {
                var3 = var12;
                throw var12;
            } finally {
                if (writer != null) {
                    if (var3 != null) {
                        try {
                            writer.close();
                        } catch (Throwable var11) {
                            var3.addSuppressed(var11);
                        }
                    } else {
                        writer.close();
                    }
                }

            }

        }
    }

    @Override
    public int getPreviewCameraId() {
        return deviceConfig.previewCameraId;
    }

    @Override
    public int getPreviewCameraOrientation() {
        return deviceConfig.previewCameraOrientation;
    }

    @Override
    public int getInfraredCameraId() {
        return deviceConfig.infraredCameraId;
    }

    @Override
    public int getPreviewCameraRotateDegree() {
        return deviceConfig.previewCameraRotateDegree;
    }

    @Override
    public int getInfraredCameraRotateDegree() {
        return deviceConfig.infraredCameraRotateDegree;
    }

    @Override
    public boolean isPreviewCameraFlipX() {
        return deviceConfig.previewCameraFlipX;
    }

    @Override
    public boolean isPreviewCameraFlipY() {
        return deviceConfig.previewCameraFlipY;
    }

    @Override
    public boolean isInfraredCameraFlipX() {
        return deviceConfig.infraredCameraFlipX;
    }

    @Override
    public boolean isInfraredCameraFlipY() {
        return deviceConfig.infraredCameraFlipY;
    }

    @Override
    public int getInfraredCameraOffsetX() {
        return deviceConfig.infraredCameraOffsetX;
    }

    @Override
    public int getInfraredCameraOffsetY() {
        return deviceConfig.infraredCameraOffsetY;
    }

    @Override
    public int getCameraZoom() {
        return deviceConfig.cameraZoom;
    }

    @Override
    public int getPreviewWidth() {
        return deviceConfig.previewWidth;
    }

    @Override
    public int getPreviewHeight() {
        return deviceConfig.previewHeight;
    }
//
//    @Override
//    public EntranceDriver getEntranceDriver() {
//        return DeviceDriverFactory.getEntranceDriver(deviceConfig.entranceDriverName);
//    }
//
//    @Override
//    public LedDriver getLedDriver() {
//        return DeviceDriverFactory.getLedDriver(deviceConfig.ledDriverName);
//    }
//
//    @Override
//    public boolean setAwakeSleepListener(AwakeSleepListener listener) {
//        if (listener == null) {
//            return false;
//        }
//        AwakeSleepSensor awakeSleepSensor = DeviceDriverFactory.getAwakeSleepSensor(deviceConfig.awakeSleepSensorName);
//        if (awakeSleepSensor == null) {
//            return false;
//        }
//        awakeSleepSensor.setListener(listener);
//        return true;
//    }


    private static class DeviceConfig {
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
    }
}
