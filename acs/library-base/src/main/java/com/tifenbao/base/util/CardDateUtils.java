package com.tifenbao.base.util;

import com.tifenbao.base.bean.index.AttendanceTimeBean;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 人脸识别考情模式判断规则uitls
 * <p>
 * mar
 * 2019/8/25
 */
public class CardDateUtils {

    public static long mormingCometTimeNormal;
    public static long mormingCometTime;
    public static long mormingCometTimeLate;
    public static long mormingLeaveTime;
    public static long mormingLeaveTimeLate;
    public static long afternoonComeTimeNormal;
    public static long afternoonComeTime;
    public static long afternoonComeTimeLate;
    public static long afternoonLeaveTime;
    public static long afternoonLeaveTimeLate;

    public static final int NOT_ATTENDANCE_STATUS = 0;//默认无考情模式0
    public static final int MORMING_COME_STATUS = 1;//1早上进
    public static final int MORMING_LEAVE_STATUS = 2;//2早上退
    public static final int AFTERNOON_COME_STATUS = 3;//3下午进
    public static final int AFTERNOON_LEAVE_STATUS = 4;//4下午退

    public static final int STATUS_NO = 10;//未到
    public static final int STATUS_COME = 11;//正常签到
    public static final int STATUS_LATE = 12;//迟到
    public static final int STATUS_LEAVE = 13;//离校

    public static boolean isOpenDialog = false;//是否打开

    public static int attendanceTimeStatus = 0;//默认无考情模式0,1早上进，2早上退，3下午进，4下午退

    /**
     * 初始化考情规则
     */
    public static void setAttendanceTime(AttendanceTimeBean attendanceTimeBean) {

        if (attendanceTimeBean == null) {
            return;
        }

        long duration = attendanceTimeBean.getDuration() * 60 * 1000;//考情分钟，用于计算考勤时间

        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(d);//当天实际

        //-------------早上考勤
        String mormingCome = date + " " + attendanceTimeBean.getMorning_come();
        mormingCometTime = strToDateLong(mormingCome).getTime();
        mormingCometTimeNormal = mormingCometTime - duration;
        mormingCometTimeLate = mormingCometTime + duration;

        //------------早上离开
        String mormingLeave = date + " " + attendanceTimeBean.getMorning_leave();
        mormingLeaveTime = strToDateLong(mormingLeave).getTime();
        mormingLeaveTimeLate = mormingLeaveTime + duration;

        //----------下午考勤
        String afternoonCome = date + " " + attendanceTimeBean.getAfternoon_come();
        afternoonComeTime = strToDateLong(afternoonCome).getTime();
        afternoonComeTimeNormal = afternoonComeTime - duration;
        afternoonComeTimeLate = afternoonComeTime + duration;

        //-----------下午离开
        String afternoonLeave = date + " " + attendanceTimeBean.getAfternoon_leave();
        afternoonLeaveTime = strToDateLong(afternoonLeave).getTime();
        afternoonLeaveTimeLate = afternoonLeaveTime + duration;
    }


    /**
     * 判断当前考情状态
     */
    public static boolean checkAttendanceTime(long time) {

        if (time >= mormingCometTimeNormal && time <= mormingCometTime) {//早上考勤模式
            attendanceTimeStatus = MORMING_COME_STATUS;
            return true;
        } else if (time > mormingCometTime && time <= mormingCometTimeLate) {//早上考勤迟到模式
            attendanceTimeStatus = MORMING_COME_STATUS;
            return false;
        } else if (time >= mormingLeaveTime && time < mormingLeaveTimeLate) {//早上离开模式
            attendanceTimeStatus = MORMING_LEAVE_STATUS;
            return true;
        } else if (time >= afternoonComeTimeNormal && time < afternoonComeTime) {//下午考勤模式
            attendanceTimeStatus = AFTERNOON_COME_STATUS;
            return true;
        } else if (time > afternoonComeTime && time <= afternoonComeTimeLate) {//下午考勤迟到模式
            attendanceTimeStatus = AFTERNOON_COME_STATUS;
            return false;
        } else if (time >= afternoonLeaveTime && time < afternoonLeaveTimeLate) {//下午离开模式
            attendanceTimeStatus = AFTERNOON_LEAVE_STATUS;
            return true;
        }

        attendanceTimeStatus = NOT_ATTENDANCE_STATUS;

        return false;
    }


    /**
     * 学生识别时判断当前考勤状态并返回对应状态
     */
    public static int checkStudentStatus(long time) {

        int status = STATUS_COME;//默认是考勤

        switch (attendanceTimeStatus) {

            case NOT_ATTENDANCE_STATUS:

                break;
            case MORMING_COME_STATUS:

                if (time <= mormingCometTime) {
                    status = STATUS_COME;
                } else {
                    status = STATUS_LATE;
                }

                break;
            case MORMING_LEAVE_STATUS:

                status = STATUS_LEAVE;

                break;
            case AFTERNOON_COME_STATUS:

                if (time <= afternoonComeTime) {
                    status = STATUS_COME;
                } else {
                    status = STATUS_LATE;
                }

                break;
            case AFTERNOON_LEAVE_STATUS:

                status = STATUS_LEAVE;

                break;
        }

        return status;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

}
