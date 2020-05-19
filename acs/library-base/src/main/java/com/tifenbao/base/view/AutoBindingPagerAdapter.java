package com.tifenbao.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifenbao.base.view.autoscrollviewpager.AutoScrollPagerAdapter;

/**
 * 专用于databinding+autoScroll
 */
public class AutoBindingPagerAdapter extends AutoScrollPagerAdapter {

    private int layoutId;
    private int variableId;

    public AutoBindingPagerAdapter(int layoutId, int variableId) {
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        View view = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), layoutId, container, false).getRoot();
        ViewDataBinding bind = DataBindingUtil.bind(view);
        bind.setVariable(variableId, mData.get(position));

        return view;
    }

    @Override
    public void bindView(View view, int position) {

    }
}
