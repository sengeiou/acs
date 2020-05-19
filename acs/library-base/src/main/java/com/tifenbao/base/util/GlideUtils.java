package com.tifenbao.base.util;

import android.content.Context;
import android.widget.ImageView;

import com.tifenbao.base.R;
import com.tifenbao.base.network.UrlPath;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * mar
 * 2019/8/11
 */
public class GlideUtils {


    public static void setImageUrl(Context context, String path, ImageView imageView) {

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.defpic); //添加占位图
        options.error(R.drawable.defpic);
//        options.diskCacheStrategy(DiskCacheStrategy.NONE);
//        options.skipMemoryCache(true);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

}
