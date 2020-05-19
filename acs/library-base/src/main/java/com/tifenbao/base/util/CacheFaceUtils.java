package com.tifenbao.base.util;


import android.text.TextUtils;

import com.tifenbao.base.bean.CacheStudentImgBean;
import com.tifenbao.base.bean.acs.PersionBean;
import com.tifenbao.base.bean.index.IndexBean;
import com.tifenbao.base.bean.index.StudentBean;
import com.tifenbao.newfacemanager.NativeFaceConstant;

import java.util.List;

/**
 * mar
 * 2019/8/23
 */
public class CacheFaceUtils {

    private CacheStudentImgBean studentImgBean;
    private CacheUtils cacheUtils;
    private static CacheFaceUtils cacheFaceUtils;

    public static CacheFaceUtils getInstance() {

        if (cacheFaceUtils == null) {
            cacheFaceUtils = new CacheFaceUtils();
        }
        return cacheFaceUtils;
    }

    public CacheFaceUtils() {
        cacheUtils = new CacheUtils();
        studentImgBean = (CacheStudentImgBean) cacheUtils.getAsObject(CacheUtils.IMAGE_FACE_URL);
    }


    /**
     * 专门用于更新人脸文件缓存
     */
    public PersionBean setCacheFaceImage(int code, List<PersionBean> persionBeanList, String filename, String id, long faceId) {

        if (studentImgBean != null && studentImgBean.getStudentBeanList() != null && code >= 0) {
            for (PersionBean studentBean : studentImgBean.getStudentBeanList()) {//for循环旧数据

                if (!TextUtils.isEmpty(id)&&id.equals(studentBean.getGuid())) {//修改成本地人脸地址
                    studentBean.setPhoto(filename);
                    studentBean.setCode(code);
                    if (code == 0) {
                        studentBean.setFaceId(faceId);
                    }
                    break;
                }

            }
        }


        return setCacheFaceImage(persionBeanList);
    }

    /**
     * 专门用于更新人脸文件缓存
     */
    public PersionBean setCacheFaceImage(List<PersionBean> persionBeanList) {

        if (studentImgBean == null || studentImgBean.getStudentBeanList().isEmpty()) {//如果一开始人脸缓存数据为空

            if (studentImgBean == null) {
                studentImgBean = new CacheStudentImgBean();
            }

            if (persionBeanList != null && !persionBeanList.isEmpty()) {
                studentImgBean.setStudentBeanList(persionBeanList);
                return persionBeanList.get(0);//返回第0个数据
            }
        } else {
            if (persionBeanList != null && !persionBeanList.isEmpty()) {//判断新数据是否有效

                for (PersionBean indexStudentBean : persionBeanList) {//for循环新数据

                    if (TextUtils.isEmpty(indexStudentBean.getGuid())) {
                        continue;
                    }

                    boolean isNewStudent = true;//是否新学生

                    for (PersionBean studentBean : studentImgBean.getStudentBeanList()) {//for循环旧数据

                        if (TextUtils.isEmpty(studentBean.getGuid())) {
                            continue;
                        }

                        if (indexStudentBean.getGuid().equals(studentBean.getGuid())) {//新数据id等于旧数据id

                            if (indexStudentBean.getUpdate_time() > studentBean.getUpdate_time()) {//如果新数据更新了，要替换旧数据

                                studentBean.setUpdate_time(indexStudentBean.getUpdate_time());
                                studentBean.setPhoto(indexStudentBean.getPhoto());
                                return studentBean;

                            } else {//如果数据不需要更新 break出，且非新学生

                                if (studentBean.getCode() == -1) {//判断还未被人脸识别
                                    return studentBean;
                                } else {
                                    isNewStudent = false;
                                    break;
                                }
                            }

                        }

                    }

                    if (isNewStudent) {//如果是新学生，写入旧数据中
                        studentImgBean.getStudentBeanList().add(indexStudentBean);
                        return indexStudentBean;
                    }

                }

            }
        }

        cacheUtils.put(CacheUtils.IMAGE_FACE_URL, studentImgBean);//将更改后数据重新注入缓存

        return null;
    }


    public int getSuccessFace() {
        int number = 0;

        if (studentImgBean == null || studentImgBean.getStudentBeanList() == null) {
            return number;
        }

        for (PersionBean studentBean : studentImgBean.getStudentBeanList()) {
            if (studentBean.getCode() == NativeFaceConstant.SUCCESS) {
                number++;
            }
        }
        return number;
    }

    public int getErrorFace() {
        int number = 0;

        if (studentImgBean == null || studentImgBean.getStudentBeanList() == null) {
            return number;
        }

        for (PersionBean studentBean : studentImgBean.getStudentBeanList()) {
            if (studentBean.getCode() == NativeFaceConstant.ERROR || studentBean.getCode() == NativeFaceConstant.UNNETWORK) {
                number++;
            }
        }
        return number;
    }

}
