package com.tifenbao.base.view.dialog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.index.ImageBean;
import com.tifenbao.base.util.GlideUtils;

import java.util.List;

/**
 * mar
 * 2019/8/16
 */
public class HomeWorkViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ImageBean> listViews;

    public HomeWorkViewPagerAdapter(Context context, List<ImageBean> listViews) {
        this.listViews = listViews;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listViews == null ? 0 : listViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_fullimg, null);
        ImageBean imageBean = listViews.get(position);
        view.setTag(position); // Add tag

        ImageView imageView = view.findViewById(R.id.fullimg);

        GlideUtils.setImageUrl(context, imageBean.getUrl(), imageView);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
