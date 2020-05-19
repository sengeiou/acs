package com.tifenbao.base.view.autoscrollviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;

import com.tifenbao.mvvmhabit.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * Created by Caesar on 2015/10/22.
 * 轮播ViewPager
 */
public class AutoScrollViewPager extends ViewPager {

    private OnPageChangeListener mListener;
    private CarouselHandler mHandler;
    private FixedSpeedScroller mScroller;
    private AutoScrollPagerAdapter mAdapter;

    public AutoScrollViewPager(Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setOnPageChangeListener(listener);
        mHandler = new CarouselHandler(this);
        setScroller();
    }

    private static final Interpolator sInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };

    private void setScroller() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            mScroller = new FixedSpeedScroller(getContext(), sInterpolator);
            field.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置轮播时动画时长
     */
    public void setDuration(int time) {
        mScroller.setmDuration(time);
    }

    public int getDuration() {
        return mScroller.getmDuration();
    }

    /**
     * 开启轮播
     */
    public void start() {
        mHandler.start();
    }

    /**
     * 停止轮播
     */
    public void stop() {
        mHandler.stop();
    }

    /**
     * 设置自动翻页间隔时间
     */
    public void setDelay(long delay) {
        mHandler.setDelay(delay);
    }

    private OnPageChangeListener listener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mListener != null) {
                mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mListener != null) {
                mListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mHandler.stop();
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    mHandler.start();
                    break;
            }
            if (mListener != null) {
                mListener.onPageScrollStateChanged(state);
            }
        }
    };

    @Override
    @Deprecated
    public void setAdapter(PagerAdapter adapter) {
        throw new UnsupportedOperationException("use setAdapter(AutoScrollPagerAdapter) instead");
    }

    public void setAdapter(AutoScrollPagerAdapter adapter) {
        super.setAdapter(adapter);
        mAdapter = adapter;
        adapter.setViewPager(this);
        setFirstPage();
    }

    public void setFirstPage() {
        try {
            Field mCurItem = ViewPager.class.getDeclaredField("mCurItem");
            mCurItem.setAccessible(true);
            mCurItem.set(this, getFirst());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂时设置初始位置为40左右
     * 如果数据集合为空不创建视图，需要作调整,{@link AutoScrollPagerAdapter#getCount()}
     */
    public int getFirst() {
        if (mAdapter == null || mAdapter.size() == 0) {
            return 40; // 如果数据集合为空也不创建视图，这里需要作调整
        }
        return 40 - 40 % mAdapter.size();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mHandler != null) {
            mHandler.stop();
        }
        super.onDetachedFromWindow();
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.mListener = listener;
    }

    static class CarouselHandler extends Handler {
        private static final int WHAT = 1000;
        private static final long DELAY = 2000;
        private WeakReference<AutoScrollViewPager> pagerWeakReference;
        private long delay;

        public CarouselHandler(AutoScrollViewPager viewPager) {
            pagerWeakReference = new WeakReference<>(viewPager);
            delay = DELAY;
        }

        @Override
        public void handleMessage(Message msg) {
            AutoScrollViewPager viewPager = pagerWeakReference.get();
            if (viewPager != null && viewPager.getAdapter() != null) {
                int count = viewPager.getAdapter().getCount();
                if (count < 2) {
                    return;
                }
                int item = viewPager.getCurrentItem();
                item++;
                if (item >= count) {
                    return;
                }
                if (item > 1000) {
                    viewPager.setFirstPage();
                } else {
                    viewPager.setCurrentItem(item);
                }

                sendEmptyMessageDelayed(WHAT, delay);
            } else {
                removeMessages(WHAT);
            }
        }

        public void start() {
            stop();
            sendEmptyMessageDelayed(WHAT, delay);
        }

        public void stop() {
            removeMessages(WHAT);
        }

        public void setDelay(long delay) {
            stop();
            this.delay = delay;
            start();
        }
    }

    private float mDownX;
    private float mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = Math.abs(event.getX() - mDownX);
                float deltaY = Math.abs(event.getY() - mDownY);
                if (deltaX > deltaY && deltaX > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
        }
        return super.onTouchEvent(event);
    }
}
