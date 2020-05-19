package com.android.tfb.temperature.utils;

import com.xcdz.jni.TemperatureSensor;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 温控模块
 * <p>
 * mar
 * 2020/3/22
 */
public class TemperatureUtils {

    public final static int HTPA = 0x1000;
    public final static int D6T44L06 = 0x1001;
    public final static int D6T32L01A = 0x1002;

    public int mSensorType = D6T44L06;
    private float minTemp = 35.5f;
    private float maxTemp = 40f;
    private onTemperatureListener listener;

    public interface onTemperatureListener {
        void temperatureCallBack(float temperature);
    }

    public TemperatureUtils(int mSensorType, float minTemp, float maxTemp) {
        this.mSensorType = mSensorType;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public void setListener(onTemperatureListener listener) {
        this.listener = listener;
    }

    public void getTemperature() {

        Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(ObservableEmitter<Float> emitter) throws Exception {

                float i = 0;
                float tm = 0f;
                while (i < 2) {
                    tm = readTemperature(mSensorType) * 0.1f;
                    if (tm >= minTemp && tm <= maxTemp) {
                        break;
                    }
                    i=i+0.5f;
                    Thread.sleep(200);
                }
                emitter.onNext(tm);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Float>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Float item) {
                if (listener != null) {
                    listener.temperatureCallBack(item);
                }
                System.out.println("Item is " + item);
            }
        });
    }

    private int readTemperature(int sen) {
        System.out.println("sen=" + sen);
        int devfd = TemperatureSensor.OpenTemperatureSensor();
        System.out.println("devfd=" + devfd);
        if (sen == HTPA) {
            return TemperatureSensor.ReadHtpaTemperature(devfd);
        } else if (sen == D6T44L06) {
            return TemperatureSensor.ReadD6T44L06Temperature(devfd);
        } else if (sen == D6T32L01A) {
            return TemperatureSensor.ReadD6T32L01ATemperature(devfd);
        }
        return 0xffff;
    }
}
