package com.tifenbao.base.bean.search;

import com.tifenbao.base.bean.index.CourseBean;

import java.io.Serializable;
import java.util.List;

/**
 * mar
 * 2019/8/17
 */
public class SearchStudentBean implements Serializable {

    private String id;
    private String student_name;
    private String student_number;
    private String photo;
    private List<StudentScoreBean> student_score_list;
    private StudentWeekScoreBean student_week_score;
    private List<ExamBean> exam;
    private List<MessageBean> message;
    private List<CourseBean> course;
    private float temperature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public List<StudentScoreBean> getStudent_score_list() {
        return student_score_list;
    }

    public void setStudent_score_list(List<StudentScoreBean> student_score_list) {
        this.student_score_list = student_score_list;
    }

    public StudentWeekScoreBean getStudent_week_score() {
        return student_week_score;
    }

    public void setStudent_week_score(StudentWeekScoreBean student_week_score) {
        this.student_week_score = student_week_score;
    }

    public List<ExamBean> getExam() {
        return exam;
    }

    public void setExam(List<ExamBean> exam) {
        this.exam = exam;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
