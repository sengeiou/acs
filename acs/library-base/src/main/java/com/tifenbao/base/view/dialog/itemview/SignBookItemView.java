package com.tifenbao.base.view.dialog.itemview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.signbook.ClockBean;
import com.tifenbao.base.util.GlideUtils;
import com.tifenbao.base.view.ScalableImageView;
import com.tifenbao.base.view.recycleview.base.ItemViewDelegate;
import com.tifenbao.base.view.recycleview.base.ViewHolder;

/**
 * 签到簿item
 * <p>
 * mar
 * 2019/8/1
 */
public class SignBookItemView implements ItemViewDelegate<ClockBean> {

    private Context context;

    public SignBookItemView(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_student_sign;
    }

    @Override
    public boolean isForViewType(ClockBean item, int position) {
        return true;
    }

    @Override
    public void convert(final ViewHolder holder, final ClockBean clockBean, final int position) {


        if (clockBean != null) {

            holder.setText(R.id.name, clockBean.getStudent_name());

            ScalableImageView scalableImageView = (ScalableImageView) holder.getView(R.id.pic);

            scalableImageView.setRoundConner(80);

            GlideUtils.setImageUrl(context, clockBean.getPhoto(), scalableImageView);

            LinearLayout status_bg = holder.getView(R.id.status_bg);
            LinearLayout status_layout = holder.getView(R.id.status_layout);
            TextView status = holder.getView(R.id.status);

            if (clockBean.getLeave_status() == 1) {//请假
                status_layout.setBackgroundResource(R.drawable.signbook_yellow_img);
                status.setText("请假");
                status_bg.setBackgroundResource(R.drawable.bg_dialog_yellow_side_white_radius20);
            } else if (clockBean.getLate_status() == 1) {//迟到
                status_layout.setBackgroundResource(R.drawable.signbook_red_img);
                status.setText("迟到");
                status_bg.setBackgroundResource(R.drawable.bg_dialog_red_side_white_radius20);
            } else if (clockBean.getClock_status() == 1) {//签到
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
