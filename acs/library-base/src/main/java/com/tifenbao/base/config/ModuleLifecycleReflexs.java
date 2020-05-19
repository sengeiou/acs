package com.tifenbao.base.config;

/**
 * Created by goldze on 2018/6/21 0021.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.tifenbao.base.base.BaseModuleInit";
    //班牌业务模块
    private static final String ClassModelInit = "com.tifenbao.bbt.classmodel.ClassModelInit";
    //校牌业务模块
    private static final String SchoolModelInit = "com.tifenbao.bbt.schoolmodel.SchoolModelInit";
    //宿舍业务模块
    private static final String DormModelInit = "com.tifenbao.bbt.dormmodel.DormModelInit";
    //欢迎业务模块
    private static final String WelcomeModelInit = "com.tifenbao.bbt.welcomemodel.WelcomeModelInit";
    //考试业务模块
    private static final String ExamModelInit = "com.tifenbao.bbt.exammodel.ExamModelInit";
    //校园展示业务模块
    private static final String ShowSchoolModelInit = "com.tifenbao.bbt.showschoolmodel.ShowSchoolModelInit";


//    //首页业务模块
//    private static final String HomeInit = "com.goldze.home.HomeModuleInit";
//    //工作业务模块
//    private static final String WorkInit = "com.goldze.work.WorkModuleInit";
//    //消息业务模块
//    private static final String MsgInit = "com.goldze.msg.MsgModuleInit";
//    //用户业务模块
//    private static final String UserInit = "com.goldze.user.UserModuleInit";
//
//    //自定义模块
//    private static final String DataInit = "com.goldze.data.DataModuleInit";

    public static String[] initModuleNames = {BaseInit, ClassModelInit, SchoolModelInit, DormModelInit, WelcomeModelInit, ExamModelInit, ShowSchoolModelInit};
}
