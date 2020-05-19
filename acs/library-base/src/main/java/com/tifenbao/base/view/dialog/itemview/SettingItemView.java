package com.tifenbao.base.view.dialog.itemview;

import android.content.Context;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.mode.ModeBean;
import com.tifenbao.base.view.recycleview.base.ItemViewDelegate;
import com.tifenbao.base.view.recycleview.base.ViewHolder;

/**
 * 设置item
 * <p>
 * mar
 * 2019/8/1
 */
public class SettingItemView implements ItemViewDelegate<ModeBean> {

    private Context context;

    public SettingItemView(Context context) {

        this.context = context;

    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_mode;
    }

    @Override
    public boolean isForViewType(ModeBean item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, ModeBean modeBean, int position) {


        if (modeBean != null) {
            holder.setImageResource(R.id.mode_img, modeBean.getDrawableId());
            holder.setText(R.id.mode_name, modeBean.getName());
        }

    }


}