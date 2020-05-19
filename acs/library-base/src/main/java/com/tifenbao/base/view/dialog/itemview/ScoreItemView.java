package com.tifenbao.base.view.dialog.itemview;

import android.content.Context;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.search.TypeScoreBean;
import com.tifenbao.base.view.recycleview.base.ItemViewDelegate;
import com.tifenbao.base.view.recycleview.base.ViewHolder;

/**
 * 个人信息弹窗分数item
 * <p>
 * mar
 * 2019/8/1
 */
public class ScoreItemView implements ItemViewDelegate<TypeScoreBean> {

    private Context context;

    public ScoreItemView(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_score;
    }

    @Override
    public boolean isForViewType(TypeScoreBean item, int position) {
        return true;
    }

    @Override
    public void convert(final ViewHolder holder, final TypeScoreBean typeScoreBean, final int position) {


        if (typeScoreBean != null) {

            holder.setText(R.id.exam_score, typeScoreBean.getScore_type_name() + " : " + typeScoreBean.getScore());

        }

    }

}