package com.tifenbao.base.view.dialog.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.index.ImageBean;
import com.tifenbao.base.bean.mode.ModeBean;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.view.autoscrollviewpager.AutoScrollViewPager;
import com.tifenbao.base.view.dialog.adapter.ImagePagerAdapter;
import com.tifenbao.base.view.dialog.itemview.SettingItemView;
import com.tifenbao.base.view.recycleview.MultiItemTypeAdapter;
import com.tifenbao.base.view.recycleview.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * mar
 * 2019/8/26
 */
public class SettingPopWindow {

    private static SettingPopWindow settingPopWindow;

    private Context context;
    private PopupWindow popupWindow;
    private RecyclerViewAdapter adapter;
    private TextView settingMode;
    private TextView settingSystem;

    private List<ModeBean> modeBeanList;

    private ItemModeClickListener itemModeClickListener;

    public interface ItemModeClickListener {

        void onItemMode(int mode);

    }

    public interface ItemClickClickListener {

        void onItemClick();

    }

    public static SettingPopWindow getInstance() {
        if (settingPopWindow == null) {
            settingPopWindow = new SettingPopWindow();
        }
        return settingPopWindow;
    }

    public PopupWindow setSettingPopwindow(Context context, View view, ItemModeClickListener itemModeClickListener) {

        this.context = context;
        this.itemModeClickListener = itemModeClickListener;


        View popView = View.inflate(context, R.layout.popwindow_setting, null);

        settingMode = popView.findViewById(R.id.setting_mode);

        settingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMode();
            }
        });

        settingSystem = popView.findViewById(R.id.setting_system);

        settingSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSetting();
            }
        });

        RecyclerView recyclerView = popView.findViewById(R.id.setting_recyclerview);

        modeBeanList = new ArrayList<>();

        MultiItemTypeAdapter itemAdapter = new MultiItemTypeAdapter(context, modeBeanList);

        itemAdapter.addItemViewDelegate(new SettingItemView(context));

        itemAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                if (position < modeBeanList.size()) {
                    itemModeClickListener.onItemMode(modeBeanList.get(position).getMoodType());
                }


            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        adapter = new RecyclerViewAdapter(itemAdapter);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        setMode();

        popupWindow = new PopupWindow(popView);

        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        // 设置PopupWindow的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true); // 设置是否获取焦点
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        popupWindow.showAsDropDown(view);

        return popupWindow;
    }

    public void setSetting() {

        settingSystem.setBackgroundColor(ContextCompat.getColor(context, R.color.bbt_black2));
        settingMode.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));

        modeBeanList.clear();
        modeBeanList.add(new ModeBean("班牌设置", BaseConstant.MODE_SETTING_CLASS, R.drawable.ic_s_class));
        modeBeanList.add(new ModeBean("系统设置", BaseConstant.MODE_SETTING_SYSTEM, R.drawable.ic_s_network));
        adapter.notifyDataSetChanged();
    }

    public void setMode() {
        settingMode.setBackgroundColor(ContextCompat.getColor(context, R.color.bbt_black2));
        settingSystem.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));

        modeBeanList.clear();
        modeBeanList.add(new ModeBean("班牌模式", BaseConstant.MODE_CLASS, R.drawable.ic_s_bp));
        modeBeanList.add(new ModeBean("校牌模式", BaseConstant.MODE_SCHOOL, R.drawable.ic_s_xp));
        modeBeanList.add(new ModeBean("迎宾模式", BaseConstant.MODE_WELCOME, R.drawable.ic_s_yb));
        modeBeanList.add(new ModeBean("考场模式", BaseConstant.MODE_EXAM, R.drawable.ic_s_kc));
        modeBeanList.add(new ModeBean("宿舍模式", BaseConstant.MODE_DORM, R.drawable.ic_s_ss));
        modeBeanList.add(new ModeBean("校牌展示", BaseConstant.MODE_SHOWSCHOOL, R.drawable.ic_s_ss));
        adapter.notifyDataSetChanged();
    }


    public PopupWindow setFullImgPopwindow(final Context context, View view, List<ImageBean> imageBeanList, ItemClickClickListener itemClickClickListener) {

        View popView = View.inflate(context, R.layout.dialog_fullviewpager, null);

        AutoScrollViewPager imgViewpager = popView.findViewById(R.id.full_viewpager);
        ImagePagerAdapter imgPagerAdapter = new ImagePagerAdapter(context, new ImagePagerAdapter.ItemClickListener() {
            @Override
            public void onCencel() {
                cancel();
                if (itemClickClickListener != null) {
                    itemClickClickListener.onItemClick();
                }
            }
        });
        imgViewpager.setAdapter(imgPagerAdapter);
        imgPagerAdapter.addAll(imageBeanList);
        imgPagerAdapter.notifyDataSetChanged();
        imgViewpager.setDelay(5000);
        if (imageBeanList.size() > 1) {
            imgViewpager.start();
        } else {
            imgViewpager.stop();
        }

        popupWindow = new PopupWindow(popView);

        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        // 设置PopupWindow的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false); // 设置是否获取焦点
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);

        return popupWindow;

    }


    public boolean isShow() {
        return popupWindow != null ? popupWindow.isShowing() : false;
    }

    public void cancel() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

}
