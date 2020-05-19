package com.tifenbao.base.view.autoscrollviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Caesar on 2015/10/22.
 * 轮播ViewPager适配器
 */
public abstract class AutoScrollPagerAdapter<T> extends PagerAdapter {
    private AutoScrollViewPager mViewPager;
    private LinkedList<View> mViews;
    protected List<T> mData;

    public AutoScrollPagerAdapter() {
        mViews = new LinkedList<>();
        mData = new ArrayList<>();
    }

    public boolean add(T object) {
        return mData.add(object);
    }

    public boolean addAll(List<T> collection) {
        return collection != null && mData.addAll(collection);
    }

    public void clear() {
        mData.clear();
    }

    /**
     * @see AutoScrollPagerAdapter#getRealPosition(int)
     */
    public T get(int location) {
        if (mData.isEmpty()) {
            return null;
        }
        return mData.get(getRealPosition(location));
    }

    public int size() {
        return mData.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        if (mData.size() <= 3) {
            mViews.add(view);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int size = mData.size();
        if (size > 0 && position >= size) {
            position %= size;
        }
        if (position < 0) {
            position += size;
        }
        View view;
        if (mData.size() <= 3) {
            if (!mViews.isEmpty()) {
                view = mViews.removeFirst();
            } else {
                view = getView(container, position);
            }
        } else {
            //超过3个重新inflate布局，否则错乱
            view = getView(container, position);
        }

        if (size > 0) { // 因为有可能数据集合为空时也让它创建视图，这里要作判断，不能绑定数据
            bindView(view, position);
        }
        container.addView(view);
        return view;
    }

    public abstract View getView(ViewGroup container, int position);

    public abstract void bindView(View view, int position);

    /**
     * 如果数据集合为空不创建视图，需要作调整，同时调整{@link AutoScrollViewPager#getFirst()}
     */
    @Override
    public int getCount() {
        if (mData != null && mData.size() == 1) {
            return 1;//一张图片不轮播
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    protected void setViewPager(AutoScrollViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void notifyDataSetChanged() {
        if (mViewPager != null) {
            mViewPager.setFirstPage();
        }
        super.notifyDataSetChanged();
    }

    public int getRealPosition(int position) {
        int size = mData.size();
        if (size == 0) {
            return 0;
        }
        return position % size;
    }

    public LinkedList<View> getmViews() {
        return mViews;
    }
}
