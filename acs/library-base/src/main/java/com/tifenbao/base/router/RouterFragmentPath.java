package com.tifenbao.base.router;

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by goldze on 2018/6/21
 */

public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_HOME = HOME + "/Home";
    }

    /**
     * 校园组件
     */
    public static class School {
        private static final String SCHOOL = "/school";

        public static final String PAGER_SCHOOL = SCHOOL + "/school";
    }

    /**
     * 课程表组件
     */
    public static class TimeTable {
        private static final String TIMETABLE = "/timetable";

        public static final String PAGER_TIMETABLE = TIMETABLE + "/timetable";
    }

    /**
     * 查询学生组件
     */
    public static class Search {
        private static final String SEARCH = "/search";

        public static final String PAGER_SEARCH = SEARCH + "/search";
        public static final String PAGER_STUDENTMSG = SEARCH + "/studentmsg";
    }

    /**
     * 公示组件
     */
    public static class Show {
        private static final String SHOW = "/show";

        public static final String PAGER_SHOW = SHOW + "/show";
    }

    /**
     * 相册组件
     */
    public static class Image {
        private static final String IMAGE = "/image";

        public static final String PAGER_IMAGE = IMAGE + "/show";
    }

    /**
     * 视频组件
     */
    public static class Video {
        private static final String VIDEO = "/video";

        public static final String PAGER_VIDEO = VIDEO + "/video";
    }

}
