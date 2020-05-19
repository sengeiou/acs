package com.tifenbao.base.util;

/**
 * mar
 * 2019/11/30
 */
public class NoDoubleClickUtils {

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 800;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME ) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }

}
