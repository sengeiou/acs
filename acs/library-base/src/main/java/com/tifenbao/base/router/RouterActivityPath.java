package com.tifenbao.base.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by mar on 2018/6/21
 */

public class RouterActivityPath {
    /**
     * 班牌模式
     */
    public static class MainClass {
        private static final String MAIN_CLASS = "/main_class";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_CLASS + "/Main";
    }

    /**
     * 校牌模式
     */
    public static class MainSchool {
        private static final String MAIN_SCHOOL = "/main_school";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_SCHOOL + "/Main";
    }

    /**
     * 宿舍模式
     */
    public static class MainDorm {
        private static final String MAIN_DORM = "/main_dorm";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_DORM + "/Main";
    }

    /**
     * 欢迎模式
     */
    public static class MainWelcome {
        private static final String MAIN_WELCOME = "/main_welcome";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_WELCOME + "/Main";
    }

    /**
     * 考场模式
     */
    public static class MainExam {
        private static final String MAIN_EXAM = "/main_exam";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_EXAM + "/Main";
    }

    /**
     * 校园展示模式
     */
    public static class MainShowSchool {
        private static final String MAIN_SHOWSCHOOL = "/main_showschool";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN_SHOWSCHOOL + "/Main";
    }

}
