package com.tifenbao.base.view.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tifenbao.base.view.recycleview.bean.RecyclerViewBean;

import java.util.List;


/**
 * Created by luojg on 2016/11/27.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private static final String TAG = DividerGridItemDecoration.class.toString();
    private Context mContext;
    private Drawable mDivider;
    private List<RecyclerViewBean> mDatas;


    public DividerGridItemDecoration(Context context, List<RecyclerViewBean> list) {
        mContext = context;
        if (mDivider == null) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }
        mDatas = list;
    }

    public DividerGridItemDecoration(Context context, List<RecyclerViewBean> list, int drawableId) {
        this(context, list);
        mDivider = ContextCompat.getDrawable(context, drawableId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDatas != null) {
 /*           int position = parent.getChildAdapterPosition(view); // item position
            if (mDatas.size() > position - 1) {
                RecyclerViewBean bean = mDatas.get(position);
                int height = mDivider.getIntrinsicHeight();
                int width = height;
                if (bean.getDrawDividerType() == DrawRegularListener.TYPE_NOT_DRAW_VERTICAL_AND_HORIZONTAL) {
                    outRect.right = 0;
                    outRect.bottom = 0;
                } else if (bean.getDrawDividerType() == DrawRegularListener.TYPE_NOT_DRAW_VERTICAL) {
                    outRect.right = 0;
                    outRect.bottom = height;
                } else {
                    int columnCount = bean.getSpanCount() / bean.getSpan();
                    int column = bean.getColumn();//
                    outRect.left = width - column * width / columnCount; // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = (column + 1) * width / columnCount; // (column + 1) * ((1f / spanCount) * spacing)
                    if (bean.getDrawDividerType() == DrawRegularListener.TYPE_NOT_DRAW_HORIZONTAL) {
                        outRect.top = 0;
                        outRect.bottom = 0;
                    } else {
                        //if (position < columnCount) { // top edge
                        outRect.top = height;
                        //}
                        outRect.bottom = height; // item bottom
                    }
                }
            }*/
        }
    }
}

