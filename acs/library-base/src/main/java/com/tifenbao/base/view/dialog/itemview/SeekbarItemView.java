package com.tifenbao.base.view.dialog.itemview;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.card.FormBean;
import com.tifenbao.base.view.recycleview.base.ItemViewDelegate;
import com.tifenbao.base.view.recycleview.base.ViewHolder;

/**
 * 评分弹窗item
 * <p>
 * mar
 * 2019/8/1
 */
public class SeekbarItemView implements ItemViewDelegate<FormBean> {

    private Context context;

    private SeekBarChangeListener seekBarChangeListener;

    public SeekbarItemView(Context context, SeekBarChangeListener seekBarChangeListener) {

        this.context = context;
        this.seekBarChangeListener = seekBarChangeListener;

    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_seekbar;
    }

    @Override
    public boolean isForViewType(FormBean item, int position) {
        return true;
    }

    @Override
    public void convert(final ViewHolder holder, final FormBean formBean, final int position) {


        if (formBean != null) {

            holder.setText(R.id.content, formBean.getScore_type_name());
            holder.setText(R.id.scroe, String.valueOf(formBean.getScore()));

            final SeekBar seekBar = holder.getView(R.id.seekbar);

            seekBar.setMax(formBean.getBase_score() - 1);

            seekBar.setProgress(formBean.getScore() - 1);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    holder.setText(R.id.scroe, String.valueOf(i));

                    if (seekBarChangeListener != null) {

                        seekBarChangeListener.seekChnge(position, i + 1);

                    }

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            holder.setOnClickListener(R.id.reduce, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (seekBar.getProgress() >= 1) {
                        seekBar.setProgress(seekBar.getProgress() - 1);
                    }

                }
            });

            holder.setOnClickListener(R.id.plus, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (seekBar.getProgress() < formBean.getBase_score()) {
                        seekBar.setProgress(seekBar.getProgress() + 1);
                    }

                }
            });

        }

    }

    public interface SeekBarChangeListener {

        void seekChnge(int position, int score);

    }

}