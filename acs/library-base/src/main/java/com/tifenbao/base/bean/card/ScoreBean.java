package com.tifenbao.base.bean.card;

import com.tifenbao.base.bean.search.ExamBean;
import com.tifenbao.base.bean.search.StudentWeekScoreBean;

import java.io.Serializable;
import java.util.List;

/**
 * mar
 * 2019/8/20
 */
public class ScoreBean implements Serializable {

    private StudentWeekScoreBean student_score;
    private List<ExamBean> exam;

    public StudentWeekScoreBean getStudentWeekScoreBean() {
        return student_score;
    }

    public void setStudentWeekScoreBean(StudentWeekScoreBean student_score_list) {
        this.student_score = student_score_list;
    }

    public List<ExamBean> getExam() {
        return exam;
    }

    public void setExam(List<ExamBean> exam) {
        this.exam = exam;
    }
}
