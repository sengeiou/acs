package com.tifenbao.base.view.autoscrollviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Caesar on 2015/10/28.
 * 用于替换ViewPager的Scroller
 */
public class FixedSpeedScroller extends Scroller {
    private int mDuration = 1500;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
