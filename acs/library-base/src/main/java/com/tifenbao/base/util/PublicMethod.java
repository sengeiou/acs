package com.tifenbao.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author chenshuide
 * @date 2013-9-22 下午3:20:37
 * @PublicMethod
 */
public class PublicMethod {

    public static boolean action = true;
    public static final String DESCRIPTOR = "pregnant";


    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int chineseLength(String value) {
        if (null == value) {
            return -1;
        }
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 关闭软键盘
     *
     * @param context
     */
    public static void closeInput(Context context) {
        if (((Activity) context).getCurrentFocus() != null) {
            ((InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(((Activity) context)
                                    .getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 开启键盘
     */
    public static void KeyBoardOpen(Activity activity, View view) {

        InputMethodManager inputmanger = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputmanger.showSoftInput(view, 0);
    }



    /**
     * 获取需要的屏幕宽度
     *
     * @param ctx
     * @param cutDown 需要减少的距离
     * @return px
     */
    public static int getWidthPixels(Activity ctx, int cutDown) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 计算两边的距离
        return dm.widthPixels - dip2px(ctx, cutDown);
    }


    /**
     * 获取需要的屏幕高度
     *
     * @param ctx 需要减少的距离
     * @return px
     */
    public static int getHegithPixels(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 计算两边的距离
        return dm.heightPixels;
    }

    /**
     * 获取当前手机屏幕像素
     *
     * @param ctx
     * @return
     */
    public static int[] getWidthAndHightPixels(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int[] wh = {dm.widthPixels, dm.heightPixels};
        return wh;
    }


    /**
     * 获取屏幕的分辨率
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getPhoneHeight(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getPhoneWidth(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.widthPixels;
    }

    /**
     * 检测是否有sd卡可以用
     *
     * @return
     */
    public static boolean isSDCardAvailability() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // sd card 可用
            return true;
        } else {
            // 当前不可用
            return false;
        }
    }


    /**
     * dip to px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px to dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 是否有网络
     *
     * @param context
     * @return true:有网络 false:无网络
     */
    public static boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        if (listAdapter.getCount() <= 1) {
            return;
        }

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
                MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    // 正则表达式数字验证
    public static boolean isNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("^[+-]?\\d+(\\.\\d+)?$");
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 打开键盘
     *
     * @param editText
     */
    public static void openInput(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    /**
     * 将字符串转换为整形
     *
     * @param value
     * @return
     */
    public static int integerValueOf(String value) {
        if (isNumber(value)) {
            return Integer.valueOf(value);
        } else {
            return 0;
        }
    }


    /**
     * 将字符串转换为整形
     *
     * @param value
     * @return
     */
    public static double doubleValueOf(String value) {
        if (isNumber(value)) {
            return Double.parseDouble(value);
        } else {
            return 0.0f;
        }
    }

    public static long longValueOf(String value) {
        if (isNumber(value)) {
            return Long.parseLong(value);
        } else {
            return 0L;
        }
    }

    /**
     * 判断是否为数字
     *
     * @param value
     * @return
     */
    public static String isNumber2(String value) {
        if (isNumber(value)) {
            return value;
        } else {
            return "0";
        }
    }

    /**
     * 如果如果内容为空的话，返回true
     *
     * @param content
     * @return
     */
    public static boolean isNull(String content) {
        if (TextUtils.isEmpty(content)) {
            return true;
        } else {
            if ("null".equals(content.trim().toLowerCase())) {
                return true;
            } else {
                return false;
            }

        }

    }


    // 递归
    public static long getFileSize(File f) throws Exception// 取得文件夹大小
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 计算缓存
     *
     * @param context
     * @return
     */
    public static String getCacheSize(Context context) {
        File file = context.getCacheDir();
        try {
            long fileSize = getFileSize(file);
            String number = getMbSize(fileSize);
            return number + "M  ";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00M  ";
    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearCacheSize(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                if (item.isFile()) {
                    item.delete();
                } else {
                    deleteFilesByDirectory(item);
                }
            }
        }
    }

    /**
     * 以Mb为单位保留两位小数
     *
     * @param dirSize
     * @return
     */
    public static String getMbSize(long dirSize) {
        double size = 0;
        size = (dirSize + 0.0) / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00");// 以Mb为单位保留两位小数
        String filesize = df.format(size);
        return filesize;
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }


    /**
     * method:获得当前版本
     */
    public static String getNowVersion(Context ctx) {
        if (ctx == null) {
            return "";
        }
        PackageManager manager = ctx.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        // float appVersion = new Float().floatValue();
        return "";
    }


}
