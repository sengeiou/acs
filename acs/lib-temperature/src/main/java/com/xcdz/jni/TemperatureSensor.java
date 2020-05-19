package com.xcdz.jni;

public class TemperatureSensor {
    static {
        System.loadLibrary("xctemperjni");
    }
    private  static TemperatureSensor mPrivate;
    public static TemperatureSensor getInstance()
    {
        if(mPrivate == null)
        {
            mPrivate = new TemperatureSensor();
        }
        return mPrivate;
    }

    public TemperatureSensor()
    {

    }
    public native static int OpenTemperatureSensor();
    public native static  byte []  ReadD6T44L06 (int  fd);
    public native static  int  ReadD6T44L06Temperature (int  fd);

    public native static  byte []  ReadD6T32L01A (int  fd);
    public native static  int  ReadD6T32L01ATemperature (int  fd);


    public native static  byte []  ReadHTPA (int  fd);
    public native static  int  ReadHtpaTemperature (int  fd);


}
