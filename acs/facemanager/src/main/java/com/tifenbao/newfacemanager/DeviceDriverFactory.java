package com.tifenbao.newfacemanager;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class DeviceDriverFactory {

    public static final String DEFAULT_CONFIG_FILE = new File(Environment.getExternalStorageDirectory(), "device_config.json").getAbsolutePath();

    private static final String TAG = "device_driver";

    public static FileConfigDeviceAdapter getDeviceAdapter(String configFile) {
        try {
            if (configFile == null) {
                configFile = DEFAULT_CONFIG_FILE;
            }
            return new FileConfigDeviceAdapter(new File(configFile));
        } catch (Exception e) {
            Log.e(TAG, "getDeviceAdapter: Get FileConfigDeviceAdapter ERROR", e);
            return null;
        }
    }

//    private static final Map<String, EntranceDriver> ENTRANCE_DRIVERS = new ConcurrentHashMap<>();
//
//    static {
//        ENTRANCE_DRIVERS.put("default", new DefaultEntranceDriver());
//    }
//
//    /**
//     * 自定义门驱动
//     *
//     * @param name 驱动名称
//     * @return
//     */
//    public static EntranceDriver getEntranceDriver(String name) {
//        if (name == null) {
//            return null;
//        }
//        return ENTRANCE_DRIVERS.get(name);
//    }

//    /**
//     * 注册门驱动
//     *
//     * @param name
//     * @param entranceDriver
//     * @return 前一个使用name注册的EntranceDriver
//     */
//    public static EntranceDriver registerEntranceDriver(String name, EntranceDriver entranceDriver) {
//        if (name != null && entranceDriver != null) {
//            return ENTRANCE_DRIVERS.put(name, entranceDriver);
//        }
//        return null;
//    }
//
//
//    private static final Map<String, LedDriver> LED_DRIVERS = new ConcurrentHashMap<>();
//
//    static {
//        LED_DRIVERS.put("default", new DefaultLedDriver());
//    }
//
//    /**
//     * 自定义LED驱动
//     *
//     * @param name 驱动名称
//     * @return
//     */
//    public static LedDriver getLedDriver(String name) {
//        if (name == null) {
//            return null;
//        }
//        return LED_DRIVERS.get(name);
//    }
//
//    /**
//     * 注册LED驱动
//     *
//     * @param name
//     * @param ledDriver
//     * @return 前一个使用name注册的LedDriver
//     */
//    public static LedDriver registerLedDriver(String name, LedDriver ledDriver) {
//        if (name != null && ledDriver != null) {
//            return LED_DRIVERS.get(name);
//        }
//        return null;
//    }
//
//    /**
//     * 自定义唤醒休眠感应器
//     *
//     * @param name 感应器名称
//     * @return
//     */
//    public static AwakeSleepSensor getAwakeSleepSensor(String name) {
//        return null;
//    }
}
