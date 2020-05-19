package com.tifenbao.base.network;

import android.provider.Settings;
import android.text.TextUtils;

import com.tifenbao.base.BuildConfig;
import com.tifenbao.mvvmhabit.base.BaseApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * mar
 * 2019/7/27
 */
public class BaseParams {

    private static final String key = "tfb2020";


    public static String get(String url, Map<String, String> params) {
        return map2Form(url, baseParams(params));
    }

    public static Map baseParams(Map<String, String> params) {

        if (params == null) {
            params = new HashMap<>();
        }

        String time = String.valueOf(System.currentTimeMillis());

        params.put("token", getMd5Value(key + time));

        params.put("timeline", time);

        params.put("imei", Settings.Secure.getString(BaseApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));

        params.put("version", BuildConfig.VERSION_NAME);

        return params;
    }


    /**
     * hashMap 转化成表单字符串
     *
     * @param map
     * @return
     */
    private static String map2Form(String url, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder(url);
        if (map == null || map.size() == 0) {
            return stringBuilder.toString();
        } else {
            stringBuilder.append("?");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getValue())) {
                    stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
    }


    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
