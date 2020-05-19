package com.tifenbao.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * mar
 * 2019/9/1
 */
public class BitmapUtils {

    public static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0)
//            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressToBitmap(bitmap, 200);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 压缩图片质量
     */
    public static Bitmap compressToBitmap(Bitmap bitmap,
                                          int size) {

//			String result;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inSampleSize = 4;
        options.inInputShareable = true;

        ByteArrayOutputStream baos = null;
        int quality = 100; // 压缩比0-100;
        try {

            if (bitmap == null)
                return null;

            baos = new ByteArrayOutputStream();


            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            while ((baos.toByteArray().length / 1024) > size) {
                baos.reset();

                quality -= 10;

                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }
//				byte[] bitmapBytes = baos.toByteArray();
//				result=Base64.encodeToString(bitmapBytes, Base64.DEFAULT).trim();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                options = null;

                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    public static Bitmap MirrorImage(Bitmap bmpPreview) {

        Matrix m = new Matrix();
        m.postScale(-1, 1); // 镜像水平翻转
        return Bitmap.createBitmap(bmpPreview, 0, 0, bmpPreview.getWidth(), bmpPreview.getHeight(), m, true);
    }

    public static File saveBitmapFile(Context context, Bitmap bitmap) {
        String name = context.getExternalCacheDir().getAbsolutePath() + "/01.jpg";
//        String name = Environment.getExternalStorageDirectory().getPath() + File.separator + "01.jpg";


        File file = new File(name);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String saveBitmapFileName(Context context, Bitmap bitmap) {
        String name = context.getExternalCacheDir().getAbsolutePath() +File.separator+System.currentTimeMillis()+".jpg";
//        String name = Environment.getExternalStorageDirectory().getPath() + File.separator + "01.jpg";


        File file = new File(name);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * YUV视频流格式转bitmap
     *
     * @param data YUV视频流格式
     * @return width 设置高度
     */
    public static Bitmap getYuvBitmap(int rotate, byte[] data, int width, int height) {
        Bitmap bitmap;
        //data是onPreviewFrame参数提供
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        YuvImage yuvimage = null;
        switch (rotate) {
            case 0:
                yuvimage = new YuvImage(data, ImageFormat.NV21, width, height, null);
                yuvimage.compressToJpeg(new Rect(0, 0, width, height), 100, baos);//
                break;
            case 90:
                yuvimage = new YuvImage(rotateYUV420Degree90(data, width, height), ImageFormat.NV21, height, width, null);
                yuvimage.compressToJpeg(new Rect(0, 0, height, width), 100, baos);//
                break;
            case 180:
                yuvimage = new YuvImage(rotateYUV420Degree180(data, width, height), ImageFormat.NV21, width, height, null);
                yuvimage.compressToJpeg(new Rect(0, 0, width, height), 100, baos);//
                break;
            case 270:
                yuvimage = new YuvImage(rotateYUV420Degree270(data, width, height), ImageFormat.NV21, height, width, null);
                yuvimage.compressToJpeg(new Rect(0, 0, height, width), 100, baos);//
                break;
        }
        if (yuvimage == null) {
            yuvimage = new YuvImage(data, ImageFormat.NV21, width, height, null);
            yuvimage.compressToJpeg(new Rect(0, 0, width, height), 100, baos);//
        }

        // 80--JPG图片的质量[0-100],100最高
        byte[] rawImage = baos.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        SoftReference<Bitmap> softRef = new SoftReference<Bitmap>(BitmapFactory.decodeByteArray(rawImage, 0, rawImage
                .length, options));
        bitmap = softRef.get();
        return bitmap;
    }


    public static byte[] rotateYUV420Degree90(byte[] data, int imageWidth, int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        // Rotate the Y luma
        int i = 0;
        for (int x = 0; x < imageWidth; x++) {
            for (int y = imageHeight - 1; y >= 0; y--) {
                yuv[i] = data[y * imageWidth + x];
                i++;
            }
        }
        // Rotate the U and V color components
        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (int x = imageWidth - 1; x > 0; x = x - 2) {
            for (int y = 0; y < imageHeight / 2; y++) {
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth) + x];
                i--;
                yuv[i] = data[(imageWidth * imageHeight) + (y * imageWidth)
                        + (x - 1)];
                i--;
            }
        }
        return yuv;
    }

    private static byte[] rotateYUV420Degree180(byte[] data, int imageWidth, int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        int i = 0;
        int count = 0;
        for (i = imageWidth * imageHeight - 1; i >= 0; i--) {
            yuv[count] = data[i];
            count++;
        }
        i = imageWidth * imageHeight * 3 / 2 - 1;
        for (i = imageWidth * imageHeight * 3 / 2 - 1; i >= imageWidth
                * imageHeight; i -= 2) {
            yuv[count++] = data[i - 1];
            yuv[count++] = data[i];
        }
        return yuv;
    }

    public static byte[] rotateYUV420Degree270(byte[] data, int imageWidth,
                                               int imageHeight) {
        byte[] yuv = new byte[imageWidth * imageHeight * 3 / 2];
        int nWidth = 0, nHeight = 0;
        int wh = 0;
        int uvHeight = 0;
        if (imageWidth != nWidth || imageHeight != nHeight) {
            nWidth = imageWidth;
            nHeight = imageHeight;
            wh = imageWidth * imageHeight;
            uvHeight = imageHeight >> 1;// uvHeight = height / 2
        }
        // ??Y
        int k = 0;
        for (int i = 0; i < imageWidth; i++) {
            int nPos = 0;
            for (int j = 0; j < imageHeight; j++) {
                yuv[k] = data[nPos + i];
                k++;
                nPos += imageWidth;
            }
        }
        for (int i = 0; i < imageWidth; i += 2) {
            int nPos = wh;
            for (int j = 0; j < uvHeight; j++) {
                yuv[k] = data[nPos + i];
                yuv[k + 1] = data[nPos + i + 1];
                k += 2;
                nPos += imageWidth;
            }
        }
        return rotateYUV420Degree180(yuv, imageWidth, imageHeight);
    }


}
