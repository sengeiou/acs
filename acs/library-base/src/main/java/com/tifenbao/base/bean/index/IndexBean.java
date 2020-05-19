package com.tifenbao.base.bean.index;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化接口bean类
 * mar
 */
public class IndexBean implements Serializable, Parcelable {

    private String id;//班级编号
    private String school_id;//学校编号
    private String grade_id;
    private String class_name;//班级名称
    private String status;
    private String create_date;
    private String teacher_id;
    private String class_guid;
    private String school_name;//学校名称
    private String school_camera;//校牌模式是否拍照
    private String class_camera;//班牌模式是否拍照
    private String student_num;//学生人数
    private String teacher_num;//老师人数
    private int face;//是否有人脸识别0没有，1有
    private String elective;//是否有走班模式
    private List<ListCourseBean> course;//课表
    private List<ImageBean> image;//班风班貌图片
    private List<GloryBean> glory;//班级荣誉
    private ScoreBean score;//班级评分
    private List<MessageBean> school_message;//学校通知
    private List<MessageBean> class_message;//班级通知
    private List<SchoolArticleBean> school_article;//校园文化图文
    private List<ImageBean> school_image;//校园环境图片
    private List<HomeWorkBean> homework;//作业
    private AttendanceBean attendance;//考勤统计
    private List<TeacherBean> teacher;//老师列表
    private ModeBean mode;//模式
    private String imei;
    private AnnouncementBean announcement;//公告公示
    private List<StudentBean> student_list;//学生列表
    private List<GalleryBean> gallery;//相册图库
    private List<VideoBean> video;//视频
    private AttendanceTimeBean attendance_time;//考勤规则
    private List<ClockRecordBean> clock_record;//校牌模式 打卡bean
    private String dormitory_name;
    private WelcomeBean welcome_data;//欢迎模式数据
    private ExamBean exam_data;
    private int liveness;
    private int temperature_mode;//是否有温控
    private float temperature_warning;//高温上限
    private float temperature_base;//温度补偿
    private String temperature_limit;//温度上下限

    private List<String> unread;//小红点数据

    public List<String> getUnread() {
        return unread;
    }

    public void setUnread(List<String> unread) {
        this.unread = unread;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getClass_guid() {
        return class_guid;
    }

    public void setClass_guid(String class_guid) {
        this.class_guid = class_guid;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_camera() {
        return school_camera;
    }

    public void setSchool_camera(String school_camera) {
        this.school_camera = school_camera;
    }

    public String getClass_camera() {
        return class_camera;
    }

    public void setClass_camera(String class_camera) {
        this.class_camera = class_camera;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public String getElective() {
        return elective;
    }

    public void setElective(String elective) {
        this.elective = elective;
    }

    public List<ListCourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<ListCourseBean> course) {
        this.course = course;
    }

    public List<ImageBean> getImage() {
        return image;
    }

    public void setImage(List<ImageBean> image) {
        this.image = image;
    }

    public List<GloryBean> getGlory() {
        return glory;
    }

    public void setGlory(List<GloryBean> glory) {
        this.glory = glory;
    }

    public ScoreBean getScore() {
        return score;
    }

    public void setScore(ScoreBean score) {
        this.score = score;
    }

    public List<MessageBean> getSchool_message() {
        return school_message;
    }

    public void setSchool_message(List<MessageBean> school_message) {
        this.school_message = school_message;
    }

    public List<MessageBean> getClass_message() {
        return class_message;
    }

    public void setClass_message(List<MessageBean> class_message) {
        this.class_message = class_message;
    }

    public List<SchoolArticleBean> getSchool_article() {
        return school_article;
    }

    public void setSchool_article(List<SchoolArticleBean> school_article) {
        this.school_article = school_article;
    }

    public List<ImageBean> getSchool_image() {
        return school_image;
    }

    public void setSchool_image(List<ImageBean> school_image) {
        this.school_image = school_image;
    }

    public List<HomeWorkBean> getHomework() {
        return homework;
    }

    public void setHomework(List<HomeWorkBean> homework) {
        this.homework = homework;
    }

    public AttendanceBean getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceBean attendance) {
        this.attendance = attendance;
    }

    public List<TeacherBean> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<TeacherBean> teacher) {
        this.teacher = teacher;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public AnnouncementBean getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementBean announcement) {
        this.announcement = announcement;
    }

    public List<StudentBean> getStudent_list() {
        return student_list;
    }

    public void setStudent_list(List<StudentBean> student_list) {
        this.student_list = student_list;
    }

    public List<GalleryBean> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryBean> gallery) {
        this.gallery = gallery;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public AttendanceTimeBean getAttendance_time() {
        return attendance_time;
    }

    public void setAttendance_time(AttendanceTimeBean attendance_time) {
        this.attendance_time = attendance_time;
    }

    public int getTemperature_mode() {
        return temperature_mode;
    }

    public void setTemperature_mode(int temperature_mode) {
        this.temperature_mode = temperature_mode;
    }

    public float getTemperature_warning() {
        return temperature_warning;
    }

    public void setTemperature_warning(float temperature_warning) {
        this.temperature_warning = temperature_warning;
    }

    //    public ModeBean getMode() {
//        return mode;
//    }
//
//    public void setMode(ModeBean mode) {
//        this.mode = mode;
//    }

    public List<ClockRecordBean> getClock_record() {
        return clock_record;
    }

    public void setClock_record(List<ClockRecordBean> clock_record) {
        this.clock_record = clock_record;
    }

    public String getDormitory_name() {
        return dormitory_name;
    }

    public void setDormitory_name(String dormitory_name) {
        this.dormitory_name = dormitory_name;
    }

    public String getStudent_num() {
        return student_num;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }

    public String getTeacher_num() {
        return teacher_num;
    }

    public void setTeacher_num(String teacher_num) {
        this.teacher_num = teacher_num;
    }

    public WelcomeBean getWelcome_data() {
        return welcome_data;
    }

    public void setWelcome_data(WelcomeBean welcome_data) {
        this.welcome_data = welcome_data;
    }

    public ExamBean getExam_data() {
        return exam_data;
    }

    public void setExam_data(ExamBean exam_data) {
        this.exam_data = exam_data;
    }

    public ModeBean getMode() {
        return mode;
    }

    public float getTemperature_base() {
        return temperature_base;
    }

    public void setTemperature_base(float temperature_base) {
        this.temperature_base = temperature_base;
    }

    public void setMode(ModeBean mode) {
        this.mode = mode;
    }

    public int getLiveness() {
        return liveness;
    }

    public void setLiveness(int liveness) {
        this.liveness = liveness;
    }

    public String getTemperature_limit() {
        return temperature_limit;
    }

    public void setTemperature_limit(String temperature_limit) {
        this.temperature_limit = temperature_limit;
    }

    public IndexBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.school_id);
        dest.writeString(this.grade_id);
        dest.writeString(this.class_name);
        dest.writeString(this.status);
        dest.writeString(this.create_date);
        dest.writeString(this.teacher_id);
        dest.writeString(this.class_guid);
        dest.writeString(this.school_name);
        dest.writeString(this.school_camera);
        dest.writeString(this.class_camera);
        dest.writeString(this.student_num);
        dest.writeString(this.teacher_num);
        dest.writeInt(this.face);
        dest.writeString(this.elective);
        dest.writeList(this.course);
        dest.writeList(this.image);
        dest.writeList(this.glory);
        dest.writeSerializable(this.score);
        dest.writeList(this.school_message);
        dest.writeList(this.class_message);
        dest.writeList(this.school_article);
        dest.writeList(this.school_image);
        dest.writeList(this.homework);
        dest.writeSerializable(this.attendance);
        dest.writeList(this.teacher);
        dest.writeSerializable(this.mode);
        dest.writeString(this.imei);
        dest.writeSerializable(this.announcement);
        dest.writeList(this.student_list);
        dest.writeList(this.gallery);
        dest.writeList(this.video);
        dest.writeSerializable(this.attendance_time);
        dest.writeList(this.clock_record);
        dest.writeString(this.dormitory_name);
        dest.writeSerializable(this.welcome_data);
        dest.writeSerializable(this.exam_data);
        dest.writeInt(this.liveness);
        dest.writeInt(this.temperature_mode);
        dest.writeFloat(this.temperature_warning);
        dest.writeFloat(this.temperature_base);
        dest.writeString(this.temperature_limit);
        dest.writeStringList(this.unread);
    }

    protected IndexBean(Parcel in) {
        this.id = in.readString();
        this.school_id = in.readString();
        this.grade_id = in.readString();
        this.class_name = in.readString();
        this.status = in.readString();
        this.create_date = in.readString();
        this.teacher_id = in.readString();
        this.class_guid = in.readString();
        this.school_name = in.readString();
        this.school_camera = in.readString();
        this.class_camera = in.readString();
        this.student_num = in.readString();
        this.teacher_num = in.readString();
        this.face = in.readInt();
        this.elective = in.readString();
        this.course = new ArrayList<ListCourseBean>();
        in.readList(this.course, ListCourseBean.class.getClassLoader());
        this.image = new ArrayList<ImageBean>();
        in.readList(this.image, ImageBean.class.getClassLoader());
        this.glory = new ArrayList<GloryBean>();
        in.readList(this.glory, GloryBean.class.getClassLoader());
        this.score = (ScoreBean) in.readSerializable();
        this.school_message = new ArrayList<MessageBean>();
        in.readList(this.school_message, MessageBean.class.getClassLoader());
        this.class_message = new ArrayList<MessageBean>();
        in.readList(this.class_message, MessageBean.class.getClassLoader());
        this.school_article = new ArrayList<SchoolArticleBean>();
        in.readList(this.school_article, SchoolArticleBean.class.getClassLoader());
        this.school_image = new ArrayList<ImageBean>();
        in.readList(this.school_image, ImageBean.class.getClassLoader());
        this.homework = new ArrayList<HomeWorkBean>();
        in.readList(this.homework, HomeWorkBean.class.getClassLoader());
        this.attendance = (AttendanceBean) in.readSerializable();
        this.teacher = new ArrayList<TeacherBean>();
        in.readList(this.teacher, TeacherBean.class.getClassLoader());
        this.mode = (ModeBean) in.readSerializable();
        this.imei = in.readString();
        this.announcement = (AnnouncementBean) in.readSerializable();
        this.student_list = new ArrayList<StudentBean>();
        in.readList(this.student_list, StudentBean.class.getClassLoader());
        this.gallery = new ArrayList<GalleryBean>();
        in.readList(this.gallery, GalleryBean.class.getClassLoader());
        this.video = new ArrayList<VideoBean>();
        in.readList(this.video, VideoBean.class.getClassLoader());
        this.attendance_time = (AttendanceTimeBean) in.readSerializable();
        this.clock_record = new ArrayList<ClockRecordBean>();
        in.readList(this.clock_record, ClockRecordBean.class.getClassLoader());
        this.dormitory_name = in.readString();
        this.welcome_data = (WelcomeBean) in.readSerializable();
        this.exam_data = (ExamBean) in.readSerializable();
        this.liveness = in.readInt();
        this.temperature_mode = in.readInt();
        this.temperature_warning = in.readFloat();
        this.temperature_base = in.readFloat();
        this.temperature_limit = in.readString();
        this.unread = in.createStringArrayList();
    }

    public static final Creator<IndexBean> CREATOR = new Creator<IndexBean>() {
        @Override
        public IndexBean createFromParcel(Parcel source) {
            return new IndexBean(source);
        }

        @Override
        public IndexBean[] newArray(int size) {
            return new IndexBean[size];
        }
    };
}
