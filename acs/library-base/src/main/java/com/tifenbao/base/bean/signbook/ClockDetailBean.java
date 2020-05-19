package com.tifenbao.base.bean.signbook;

import com.tifenbao.base.bean.index.AttendanceBean;

import java.io.Serializable;
import java.util.List;

/**
 * mar
 * 2019/11/9
 */
public class ClockDetailBean implements Serializable {

    private AttendanceBean clock_count;
    private List<ClockBean> clock_list;

    public AttendanceBean getClock_count() {
        return clock_count;
    }

    public void setClock_count(AttendanceBean clock_count) {
        this.clock_count = clock_count;
    }

    public List<ClockBean> getClock_list() {
        return clock_list;
    }

    public void setClock_list(List<ClockBean> clock_list) {
        this.clock_list = clock_list;
    }
}
