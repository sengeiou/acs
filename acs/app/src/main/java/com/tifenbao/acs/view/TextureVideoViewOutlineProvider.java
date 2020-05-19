package com.tifenbao.acs.view;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * mar
 * 2020/5/16
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TextureVideoViewOutlineProvider extends ViewOutlineProvider {
    private float mRadius;

    public TextureVideoViewOutlineProvider(float radius) {
        this.mRadius = radius;
    }

    @Override
    public void getOutline(View view, Outline outline) {
//        Rect rect = new Rect();
//        view.getGlobalVisibleRect(rect);
//        int leftMargin = 0;
//        int topMargin = 0;
//        Rect selfRect = new Rect(leftMargin, topMargin,
//                rect.right - rect.left - leftMargin, rect.bottom - rect.top - topMargin);
//        outline.setRoundRect(selfRect, mRadius);
        //裁剪成一个圆形
        int left0 = (view.getWidth() - view.getHeight()) / 2;
        int top0 = 0;
        int right0 = left0 + view.getHeight() ;
        int bottom0 =  view.getHeight() ;
        outline.setOval(left0, top0, right0, bottom0);
    }
}
