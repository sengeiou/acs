package com.tifenbao.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.index.ImageBean;
import com.tifenbao.base.util.GlideUtils;
import com.tifenbao.base.view.autoscrollviewpager.AutoScrollPagerAdapter;

/**
 * mar
 * 2019/7/28
 */
public class ImagePagerAdapter extends AutoScrollPagerAdapter {

    private Context context;
    private ImageListerner imageListerner;

    public ImagePagerAdapter(Context context) {
        this.context = context;
    }

    public void setImageListerner(ImageListerner imageListerner) {
        this.imageListerner = imageListerner;
    }

    public interface ImageListerner {

        void onItemImgClick();

    }

    @Override
    public View getView(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);

        return view;
    }

    @Override
    public void bindView(View view, int position) {

        if (mData.get(position) instanceof ImageBean) {
            ImageBean imageBean = (ImageBean) mData.get(position);

            ImageView imageView = view.findViewById(R.id.image);

            GlideUtils.setImageUrl(context, imageBean.getUrl(), imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (imageListerner != null) {
                        imageListerner.onItemImgClick();
                    }

                }
            });

        }


    }
}
