package com.tfb.lib_light;

import com.rockchip.rkapi.RKapi;

/**
 * mar
 * 2020/5/17
 */
public class LightUtils {

    public static void openLight(int gid,int value ){

        RKapi.setGpioValue(gid,value);
    }

}
