package com.tifenbao.base.bean.index;

import java.io.Serializable;

/**
 * 班级荣誉
 *
 * mar
 * 2019/7/27
 */
public class GloryBean  implements Serializable {

    private String class_id;
    private String glory_type_name;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getGlory_type_name() {
        return glory_type_name;
    }

    public void setGlory_type_name(String glory_type_name) {
        this.glory_type_name = glory_type_name;
    }
}
