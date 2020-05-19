package com.tifenbao.base.base;

import android.databinding.ViewDataBinding;

import com.tifenbao.base.view.dialog.view.LoadingDialog;
import com.tifenbao.mvvmhabit.base.BaseFragment;
import com.tifenbao.mvvmhabit.base.BaseViewModel;

/**
 * mar
 * 2019/7/28
 */
public abstract class BaseLandscapeFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<V, VM> {

    protected boolean isInitialization = false;//是否已经初始化
    protected LoadingDialog loadingDialog;

    abstract public void refreshDaata();

    @Override
    public void initData() {
        loadingDialog = LoadingDialog.getInstance();
        super.initData();
    }

    @Override
    public void onDestroyView() {
        isInitialization = false;
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        if (hidden) {// 不在最前端界面显示
            onPause();
        } else {// 重新显示到最前端中
            onResume();
        }

        super.onHiddenChanged(hidden);
    }
}
