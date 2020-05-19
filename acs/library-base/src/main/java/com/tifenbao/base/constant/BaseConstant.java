package com.tifenbao.base.constant;

/**
 * 基础全局性常量
 * <p>
 * <p>
 * mar
 * 2019/8/4
 */
public class BaseConstant {

    public static final String NETWORK_CHANGE = "NETWORK_CHANGE";//网络发生变化

    public static final String BASE_UPDATE_TAB = "bbt";//写死口令输入

    public static boolean IS_CHECK_UPDATE = false;//全局常量，是否检查过版本更新

    public static final long BASE_COUNTDOWN_SECOND = 60 * 1000;//基础倒计时(毫秒)

    public static final long BASE_FACE_COUNTDOWN_SECOND = 10 * 1000;//人脸识别倒计时(毫秒)

    public static int MODE = BaseConstant.MODE_SCHOOL;//全局常量，判断当前处在哪个模式

    public static boolean IS_FACE_COMMIT = false;//全局常量，人脸识别是否可行

    public static final String BASE_COMMAND = "0000";//写死口令输入

    public static final int MODE_SETTING_CLASS = 110;//班牌模式设置
    public static final int MODE_SETTING_SYSTEM = 111;//系统设置
    public static final int MODE_CLASS = 1;//班牌模式
    public static final int MODE_SCHOOL = 2;//校牌模式
    public static final int MODE_EXAM = 3;//考试模式
    public static final int MODE_WELCOME = 4;//迎宾模式
    public static final int MODE_DORM = 5;//宿舍模式
    public static final int MODE_SHOWSCHOOL = 6;//校牌展示模式
}
