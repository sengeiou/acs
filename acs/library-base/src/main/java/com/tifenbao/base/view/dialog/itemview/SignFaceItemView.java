package com.tifenbao.base.view.dialog.itemview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tifenbao.base.bean.index.StudentBean;
import com.tifenbao.base.util.CardDateUtils;
import com.tifenbao.base.util.GlideUtils;
import com.tifenbao.base.view.ScalableImageView;
import com.tifenbao.base.view.recycleview.base.ItemViewDelegate;
import com.tifenbao.base.view.recycleview.base.ViewHolder;
import com.tifenbao.base.R;

/**
 * 人脸识别签到item
 * <p>
 * mar
 * 2019/8/1
 */
public class SignFaceItemView implements ItemViewDelegate<StudentBean> {

    private Context context;

    public SignFaceItemView(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_student_sign;
    }

    @Override
    public boolean isForViewType(StudentBean item, int position) {
        return true;
    }

    @Override
    public void convert(final ViewHolder holder, final StudentBean studentBean, final int position) {


        if (studentBean != null) {

            holder.setText(R.id.name, studentBean.getStudent_name());

            ScalableImageView scalableImageView = (ScalableImageView) holder.getView(R.id.pic);

            scalableImageView.setRoundConner(80);

            GlideUtils.setImageUrl(context, studentBean.getPhoto(), scalableImageView);

            LinearLayout status_bg = holder.getView(R.id.status_bg);
            LinearLayout status_layout = holder.getView(R.id.status_layout);
            TextView status = holder.getView(R.id.status);

            if (studentBean.getStatus() == CardDateUtils.STATUS_LATE) {//迟到
                status_layout.setBackgroundResource(R.drawable.signbook_red_img);
                status.setText("迟到");
                status_bg.setBackgroundResource(R.drawable.bg_dialog_red_side_white_radius20);
            } else if (studentBean.getStatus() == CardDateUtils.STATUS_COME || studentBean.getStatus() == CardDateUtils.STATUS_LEAVE) {//签到
                status_layout.setBackgroundResource(R.drawable.signbook_green_img);
                status.setText("签到");
                status_bg.setBackgroundResource(R.drawable.bg_dialog_white_side_white_radius20);
            } else {//未到
                status_layout.setBackgroundResource(R.drawable.signbook_grey_img);
                status.setText("未到");
                status_bg.setBackgroundResource(R.drawable.bg_dialog_grey_side_white_radius20);
            }


        }

    }

}
