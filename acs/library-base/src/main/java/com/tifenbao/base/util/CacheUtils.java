package com.tifenbao.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CacheUtils {
    private static final String LOG_TAG = "CacheUtils";
    public static final int TEN_MINUTE = 10 * 60;//登录状态缓存十分钟
    public static final int TIME_HOUR = 60 * 60;
    public static final int TIME_DAY = TIME_HOUR * 24;
    private static final int MAX_SIZE = 1000 * 1000 * 50; // 50 mb
    private static final int MAX_COUNT = Integer.MAX_VALUE; // 不限制存放数据的数量
    private static Map<String, CacheUtils> mInstanceMap = new HashMap<>();

    public CacheUtils() {
    }

    private ACacheManager mCache;

    public static final String SCHOOL_NUM = "SCHOOL_NUM";//用户输入的school_num

    public static final String SDK_CODE = "SDK_CODE";//用户输入的sdk_code

    public static final String FACE_CHICK = "face_chick";//用于缓存的上传机制

    public static final String PERSION_LISTBEAN = "persion_listbean";//用于缓存的上传机制

    public static final String MODE = "mode";//app当前模式

    public static final String UID = "uid";//登录用户缓存的uid 10分钟有效期

    public static final String SCHOOL_ID = "school_id";//用户输入的school_id

    public static final String CLASS_ID = "class_id";//用户输入的class_id

    public static final String CLASSROOM_ID = "classroom_id";//用户输入的classroom_id

    public static final String DORM_ID = "dorm_id";//用户输入的dorm_id

    public static final String SYNC_TIME = "syne_time";//用户每次调用初始化数据的同步时间

    public static final String IMAGE_FACE_URL = "image_face_url";//人脸识别图片缓存


    private static ACache aCache = null;
    private static CacheUtils mCacheUtils;

    /**
     * 在application里面初始化
     *
     * @param context
     */
    public static void initialize(Context context) {
        aCache = ACache.get(context);
    }


    /**
     * 判断缓存是否没初始化
     *
     * @return 缓存是否为null
     */
    public static boolean isInitialized() {
        if (aCache != null) {
            return true;
        } else {
            Log.e(LOG_TAG, "缓存没有初始化");
            return false;
        }
    }


    public synchronized static CacheUtils get(Context ctx) {
        if (mCacheUtils == null) {
            mCacheUtils = new CacheUtils();
        }
//        return get(ctx, "PreCache");
        return mCacheUtils;
    }

    @Deprecated
    public static CacheUtils get(Context ctx, String cacheName) {
        File f = new File(ctx.getCacheDir(), cacheName);
        return get(f, MAX_SIZE, MAX_COUNT);
    }

    @Deprecated
    public static CacheUtils get(File cacheDir) {
        return get(cacheDir, MAX_SIZE, MAX_COUNT);
    }

    @Deprecated
    public static CacheUtils get(Context ctx, long max_zise, int max_count) {
        File f = new File(ctx.getCacheDir(), "ACache");
        return get(f, max_zise, max_count);
    }

    @Deprecated
    public static CacheUtils get(File cacheDir, long max_zise, int max_count) {
        CacheUtils manager = mInstanceMap.get(cacheDir.getAbsoluteFile() + myPid());
        if (manager == null) {
            manager = new CacheUtils(cacheDir, max_zise, max_count);
            mInstanceMap.put(cacheDir.getAbsolutePath() + myPid(), manager);
        }
        return manager;
    }

    private static String myPid() {
        return "_" + android.os.Process.myPid();
    }

    private CacheUtils(File cacheDir, long max_size, int max_count) {
        if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            throw new RuntimeException("can't make dirs in "
                    + cacheDir.getAbsolutePath());
        }
        mCache = new ACacheManager(cacheDir, max_size, max_count);
    }

    /**
     * put obj
     *
     * @param key
     * @param clazz
     * @param obj
     */
    public void put(String key, Class clazz, Object obj) {

//        put(key, jsonString);
        if (isInitialized()) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(obj);
            aCache.put(key, jsonString);
        }
    }

    /**
     * put obj & saveTime
     *
     * @param key
     * @param clazz
     * @param obj
     * @param saveTime
     */
    public void put(String key, Class clazz, Object obj, int saveTime) {

        if (isInitialized()) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(obj);
            aCache.put(key, jsonString, saveTime);
        }
//        put(key, Utils.newStringWithDateInfo(saveTime, jsonString));
    }

    /**
     * get obj
     *
     * @param key
     * @param clazz
     * @return
     */
    public Object getAsClass(String key, Class clazz) {
        if (isInitialized()) {
            return aCache.getAsClass(key, clazz);
        }
//        Object obj = null;
//        String jsonString = getAsString(key);
//        Gson gson = new Gson();
//        try {
//            obj = gson.fromJson(jsonString, clazz);
//        } catch (JsonSyntaxException json) {
//            json.printStackTrace();
//        }
        return null;
    }

    // =======================================
    // ============ String数据 读写 ==============
    // =======================================

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的String数据
     */
    public void put(String key, String value) {
        if (isInitialized()) {
            aCache.put(key, value);
        }
//        File file = mCache.newFile(key);
//        BufferedWriter out = null;
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(file);
//            out = new BufferedWriter(fileWriter, 1024);
//            out.write(value);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//            if (out != null) {
//                    out.flush();
//                    out.close();
//            }
//            if(fileWriter!=null){
//                 fileWriter.close();
//             }
//
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        mCache.put(file);
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的String数据
     * @param saveTime 保存的时间，单位：秒
     */
    public void put(String key, String value, int saveTime) {
//        put(key, Utils.newStringWithDateInfo(saveTime, value));
        if (isInitialized()) {
            aCache.put(key, value, saveTime);
        }
    }

    /**
     * 读取 String数据
     *
     * @param key
     * @return String 数据
     */
    public String getAsString(String key) {
        if (isInitialized()) {
            return aCache.getAsString(key);
        }

        return null;
//        File file = mCache.get(key);
//        if (!file.exists())
//            return null;
//        boolean removeFile = false;
//        BufferedReader in = null;
//        try {
//            in = new BufferedReader(new FileReader(file));
//            String readString = "";
//            String currentLine;
//            while ((currentLine = in.readLine()) != null) {
//                readString += currentLine;
//            }
//            if (!Utils.isDue(readString)) {
//                return Utils.clearDateInfo(readString);
//            } else {
//                removeFile = true;
//                return null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (removeFile)
//                remove(key);
//        }
    }

    // =======================================
    // ============= JSONObject 数据 读写 ==============
    // =======================================

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSON数据
     */
    @Deprecated
    public void put(String key, JSONObject value) {
        put(key, value.toString());
    }

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的JSONObject数据
     * @param saveTime 保存的时间，单位：秒
     */
    @Deprecated
    public void put(String key, JSONObject value, int saveTime) {
        put(key, value.toString(), saveTime);
    }

    /**
     * 读取JSONObject数据
     *
     * @param key
     * @return JSONObject数据
     */
    public JSONObject getAsJSONObject(String key) {
        String JSONString = getAsString(key);
        try {
            JSONObject obj = new JSONObject(JSONString);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // =======================================
    // ============ JSONArray 数据 读写 =============
    // =======================================

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSONArray数据
     */
    public void put(String key, JSONArray value) {
        put(key, value.toString());
    }

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的JSONArray数据
     * @param saveTime 保存的时间，单位：秒
     */
    @Deprecated
    public void put(String key, JSONArray value, int saveTime) {
        put(key, value.toString(), saveTime);
    }

    /**
     * 读取JSONArray数据
     *
     * @param key
     * @return JSONArray数据
     */
    public JSONArray getAsJSONArray(String key) {
        String JSONString = getAsString(key);
        try {
            JSONArray obj = new JSONArray(JSONString);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // =======================================
    // ============== byte 数据 读写 =============
    // =======================================

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public void put(String key, byte[] value) {
        if (isInitialized()) {
            aCache.put(key, value);
        }
//        File file = mCache.newFile(key);
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(file);
//            out.write(value);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            mCache.put(file);
//        }
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的数据
     * @param saveTime 保存的时间，单位：秒
     */
    public void put(String key, byte[] value, int saveTime) {
        if (isInitialized()) {
            aCache.put(key, value, saveTime);
        }
    }

    /**
     * 获取 byte 数据
     *
     * @param key
     * @return byte 数据
     */
    public byte[] getAsBinary(String key) {
        if (isInitialized()) {
            return aCache.getAsBinary(key);
        }
        return null;
//        RandomAccessFile RAFile = null;
//        boolean removeFile = false;
//        try {
//            File file = mCache.get(key);
//            if (!file.exists())
//                return null;
//            RAFile = new RandomAccessFile(file, "r");
//            byte[] byteArray = new byte[(int) RAFile.length()];
//            RAFile.read(byteArray);
//            if (!Utils.isDue(byteArray)) {
//                return Utils.clearDateInfo(byteArray);
//            } else {
//                removeFile = true;
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            if (RAFile != null) {
//                try {
//                    RAFile.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (removeFile)
//                remove(key);
//        }
    }

    // =======================================
    // ============= 序列化 数据 读写 ===============
    // =======================================

    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的value
     */
    public void put(String key, Serializable value) {
        put(key, value, -1);
    }

    /**
     * 保存 Serializable数据到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的value
     * @param saveTime 保存的时间，单位：秒
     */
    public void put(String key, Serializable value, int saveTime) {
        if (isInitialized()) {
            aCache.put(key, value, saveTime);
        }
//        ByteArrayOutputStream baos = null;
//        ObjectOutputStream oos = null;
//        try {
//            baos = new ByteArrayOutputStream();
//            oos = new ObjectOutputStream(baos);
//            oos.writeObject(value);
//            byte[] data = baos.toByteArray();
//            if (saveTime != -1) {
//                put(key, data, saveTime);
//            } else {
//                put(key, data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(oos!=null){
//                    oos.close();
//                }
//            } catch (IOException e) {
//            }
//        }
    }

    /**
     * 读取 Serializable数据
     *
     * @param key
     * @return Serializable 数据
     */
    public Object getAsObject(String key) {
        byte[] data = getAsBinary(key);
        if (data != null) {
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                bais = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bais);
                Object reObject = ois.readObject();
                return reObject;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    // =======================================
    // ============== bitmap 数据 读写 =============
    // =======================================

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的bitmap数据
     */
    public void put(String key, Bitmap value) {
        put(key, Utils.Bitmap2Bytes(value));
    }

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的 bitmap 数据
     * @param saveTime 保存的时间，单位：秒
     */
    public void put(String key, Bitmap value, int saveTime) {
        put(key, Utils.Bitmap2Bytes(value), saveTime);
    }

    /**
     * 读取 bitmap 数据
     *
     * @param key
     * @return bitmap 数据
     */
    public Bitmap getAsBitmap(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return Utils.Bytes2Bimap(getAsBinary(key));
    }

    // =======================================
    // ============= drawable 数据 读写 =============
    // =======================================

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的drawable数据
     */
    public void put(String key, Drawable value) {
        put(key, Utils.drawable2Bitmap(value));
    }

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key      保存的key
     * @param value    保存的 drawable 数据
     * @param saveTime 保存的时间，单位：秒
     */
    public void put(String key, Drawable value, int saveTime) {
        put(key, Utils.drawable2Bitmap(value), saveTime);
    }


    /**
     * 获取缓存文件
     *
     * @param key
     * @return value 缓存的文件
     */
    @Deprecated
    public File file(String key) {
        File f = mCache.newFile(key);
        if (f.exists()) {
            return f;
        }
        return null;
    }

    /**
     * 移除某个key
     *
     * @param key
     * @return 是否移除成功
     */
    public boolean remove(String key) {
        if (isInitialized()) {
            return aCache.remove(key);
        }
        return false;
//        return mCache.remove(key);
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        if (isInitialized()) {
            aCache.clear();
        }
//        mCache.clear();
    }

    /**
     * @author 杨福海（michael） www.yangfuhai.com
     * @version 1.0
     * @title 缓存管理器
     */
    public class ACacheManager {
        private final AtomicLong cacheSize;
        private final AtomicInteger cacheCount;
        private final long sizeLimit;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates = Collections
                .synchronizedMap(new HashMap<File, Long>());
        protected File cacheDir;

        private ACacheManager(File cacheDir, long sizeLimit, int countLimit) {
            this.cacheDir = cacheDir;
            this.sizeLimit = sizeLimit;
            this.countLimit = countLimit;
            cacheSize = new AtomicLong();
            cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        /**
         * 计算 cacheSize和cacheCount
         */
        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int size = 0;
                    int count = 0;
                    File[] cachedFiles = cacheDir.listFiles();
                    if (cachedFiles != null) {
                        for (File cachedFile : cachedFiles) {
                            size += calculateSize(cachedFile);
                            count += 1;
                            if (cachedFile != null) {
                                lastUsageDates.put(cachedFile,
                                        cachedFile.lastModified());
                            }
                        }
                        cacheSize.set(size);
                        cacheCount.set(count);
                    }
                }
            }).start();
        }

        @Deprecated
        private void put(File file) {
            if (file != null) {
                int curCacheCount = cacheCount.get();
                while (curCacheCount + 1 > countLimit) {
                    long freedSize = removeNext();
                    cacheSize.addAndGet(-freedSize);

                    curCacheCount = cacheCount.addAndGet(-1);
                }
                cacheCount.addAndGet(1);

                long valueSize = calculateSize(file);
                long curCacheSize = cacheSize.get();
                while (curCacheSize + valueSize > sizeLimit) {
                    long freedSize = removeNext();
                    curCacheSize = cacheSize.addAndGet(-freedSize);
                }
                cacheSize.addAndGet(valueSize);

                Long currentTime = System.currentTimeMillis();
                file.setLastModified(currentTime);
                lastUsageDates.put(file, currentTime);
            }

        }

        @Deprecated
        private File get(String key) {
            File file = newFile(key);
            Long currentTime = System.currentTimeMillis();
            file.setLastModified(currentTime);
            lastUsageDates.put(file, currentTime);

            return file;
        }

        private File newFile(String key) {
            return new File(cacheDir, key.hashCode() + "");
        }

        @Deprecated
        private boolean remove(String key) {
            File image = get(key);
            return image.delete();
        }

        @Deprecated
        private void clear() {
            lastUsageDates.clear();
            cacheSize.set(0);
            File[] files = cacheDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    f.delete();
                }
            }
        }

        /**
         * 移除旧的文件
         *
         * @return
         */
        private long removeNext() {
            if (lastUsageDates.isEmpty()) {
                return 0;
            }

            Long oldestUsage = null;
            File mostLongUsedFile = null;
            Set<Entry<File, Long>> entries = lastUsageDates.entrySet();
            synchronized (lastUsageDates) {
                for (Entry<File, Long> entry : entries) {
                    if (mostLongUsedFile == null) {
                        mostLongUsedFile = entry.getKey();
                        oldestUsage = entry.getValue();
                    } else {
                        Long lastValueUsage = entry.getValue();
                        if (lastValueUsage < oldestUsage) {
                            oldestUsage = lastValueUsage;
                            mostLongUsedFile = entry.getKey();
                        }
                    }
                }
            }

            long fileSize = calculateSize(mostLongUsedFile);
            if (mostLongUsedFile != null && mostLongUsedFile.delete()) {
                lastUsageDates.remove(mostLongUsedFile);
            }
            return fileSize;
        }

        private long calculateSize(File file) {
            if (file == null) {
                return 0L;
            } else {
                return file.length();
            }
        }
    }

    /**
     * @author 杨福海（michael） www.yangfuhai.com
     * @version 1.0
     * @title 时间计算工具类
     */
    private static class Utils {

        /**
         * 判断缓存的String数据是否到期
         *
         * @param str
         * @return true：到期了 false：还没有到期
         */
        private static boolean isDue(String str) {
            return isDue(str.getBytes());
        }

        /**
         * 判断缓存的byte数据是否到期
         *
         * @param data
         * @return true：到期了 false：还没有到期
         */
        private static boolean isDue(byte[] data) {
            String[] strs = getDateInfoFromDate(data);
            if (strs != null && strs.length == 2) {
                String saveTimeStr = strs[0];
                while (saveTimeStr.startsWith("0")) {
                    saveTimeStr = saveTimeStr
                            .substring(1, saveTimeStr.length());
                }
                long saveTime = Long.valueOf(saveTimeStr);
                long deleteAfter = Long.valueOf(strs[1]);
                if (System.currentTimeMillis() > saveTime + deleteAfter * 1000) {
                    return true;
                }
            }
            return false;
        }

        private static String newStringWithDateInfo(int second, String strInfo) {
            return createDateInfo(second) + strInfo;
        }

        private static byte[] newByteArrayWithDateInfo(int second, byte[] data2) {
            byte[] data1 = createDateInfo(second).getBytes();
            byte[] retdata = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, retdata, 0, data1.length);
            System.arraycopy(data2, 0, retdata, data1.length, data2.length);
            return retdata;
        }

        private static String clearDateInfo(String strInfo) {
            if (strInfo != null && hasDateInfo(strInfo.getBytes())) {
                strInfo = strInfo.substring(strInfo.indexOf(mSeparator) + 1,
                        strInfo.length());
            }
            return strInfo;
        }

        private static byte[] clearDateInfo(byte[] data) {
            if (hasDateInfo(data)) {
                return copyOfRange(data, indexOf(data, mSeparator) + 1,
                        data.length);
            }
            return data;
        }

        private static boolean hasDateInfo(byte[] data) {
            return data != null && data.length > 15 && data[13] == '-'
                    && indexOf(data, mSeparator) > 14;
        }

        private static String[] getDateInfoFromDate(byte[] data) {
            if (hasDateInfo(data)) {
                String saveDate = new String(copyOfRange(data, 0, 13));
                String deleteAfter = new String(copyOfRange(data, 14,
                        indexOf(data, mSeparator)));
                return new String[]{saveDate, deleteAfter};
            }
            return null;
        }

        private static int indexOf(byte[] data, char c) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] copyOfRange(byte[] original, int from, int to) {
            int newLength = to - from;
            if (newLength < 0) {
                throw new IllegalArgumentException(from + " > " + to);
            }
            byte[] copy = new byte[newLength];
            System.arraycopy(original, from, copy, 0,
                    Math.min(original.length - from, newLength));
            return copy;
        }

        private static final char mSeparator = ' ';

        private static String createDateInfo(int second) {
            String currentTime = System.currentTimeMillis() + "";
            while (currentTime.length() < 13) {
                currentTime = "0" + currentTime;
            }
            return currentTime + "-" + second + mSeparator;
        }

        /*
         * Bitmap → byte[]
         */
        private static byte[] Bitmap2Bytes(Bitmap bm) {
            if (bm == null) {
                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        }

        /*
         * byte[] → Bitmap
         */
        private static Bitmap Bytes2Bimap(byte[] b) {
            if (b.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }

        /*
         * Drawable → Bitmap
         */
        private static Bitmap drawable2Bitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            // 取 drawable 的长宽
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            // 取 drawable 的颜色格式
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
            // 建立对应 bitmap
            Bitmap bitmap = Bitmap.createBitmap(w, h, config);
            // 建立对应 bitmap 的画布
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            // 把 drawable 内容画到画布中
            drawable.draw(canvas);
            return bitmap;
        }


    }

}
