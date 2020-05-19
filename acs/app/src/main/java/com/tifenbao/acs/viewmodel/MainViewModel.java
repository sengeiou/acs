package com.tifenbao.acs.viewmodel;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.tifenbao.base.bean.index.AttendanceBean;
import com.tifenbao.mvvmhabit.base.BaseViewModel;
/**
 * Created by mar on 2018/6/21.
 */

public class MainViewModel extends BaseViewModel {

    public ObservableField<String> faceName = new ObservableField();
    public ObservableField<String> faceTemp = new ObservableField();
    public ObservableField<String> faceTime = new ObservableField();

    public ObservableField<String> peopleNum = new ObservableField();
    public ObservableField<String> imgNum = new ObservableField();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public void setImgData(int num){
        imgNum.set("图片数："+num);
    }

    public void setPeopleData(int num){
        peopleNum.set("人数："+num);
    }


    @Override
    public void onDestroy() {

        //RxBus解耦组件通信
//        RxBus.getDefault().post(new _Data(dataObservable.get()));
        super.onDestroy();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
