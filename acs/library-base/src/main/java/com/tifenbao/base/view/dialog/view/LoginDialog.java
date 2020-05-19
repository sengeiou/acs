package com.tifenbao.base.view.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tifenbao.base.R;
import com.tifenbao.base.bean.card.FormBean;
import com.tifenbao.base.bean.card.ScoreBean;
import com.tifenbao.base.bean.index.HomeWorkBean;
import com.tifenbao.base.bean.index.ImageBean;
import com.tifenbao.base.bean.index.MessageBean;
import com.tifenbao.base.bean.search.ExamBean;
import com.tifenbao.base.bean.signbook.ClockBean;
import com.tifenbao.base.bean.signbook.ClockDetailBean;
import com.tifenbao.base.constant.BaseConstant;
import com.tifenbao.base.util.CacheUtils;
import com.tifenbao.base.view.dialog.adapter.HomeWorkViewPagerAdapter;
import com.tifenbao.base.view.dialog.itemview.ScoreItemView;
import com.tifenbao.base.view.dialog.itemview.SeekbarItemView;
import com.tifenbao.base.view.dialog.itemview.SignBookItemView;
import com.tifenbao.base.view.recycleview.MultiItemTypeAdapter;
import com.tifenbao.base.view.recycleview.RecyclerViewAdapter;
import com.tifenbao.mvvmhabit.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 弹窗集合类
 * <p>
 * mar
 * 2019/8/4
 */
public class LoginDialog extends BaseDialog {

    public static LoginDialog loginDialog;

    private TextView title;
    private TextView commandText;
    private LinearLayout passwordLayout;
    private EditText userEdit;
    private EditText passwordEdit;
    private boolean isCommand = false;

    public static LoginDialog getInstance() {
        if (loginDialog == null) {
            loginDialog = new LoginDialog();
        }
        return loginDialog;
    }

    /**
     * 登录弹窗
     */
    public Dialog getLoginDialog(Context context, final DialogOnClickListener listener) {
        cancel();

        isCommand = false;

        this.context = context;

        View dialogview = View.inflate(context, R.layout.dialog_login, null);

        title = dialogview.findViewById(R.id.dialog_title);

        commandText = dialogview.findViewById(R.id.command);

        passwordLayout = dialogview.findViewById(R.id.password_layout);

        userEdit = dialogview.findViewById(R.id.user_edit);

        userEdit.addTextChangedListener(textWatcher);

        passwordEdit = dialogview.findViewById(R.id.password_edit);

        passwordEdit.addTextChangedListener(textWatcher);

        dialogview.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(userEdit.getText().toString())) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }

                if (!isCommand && TextUtils.isEmpty(passwordEdit.getText().toString())) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }

                if (isCommand && !BaseConstant.BASE_COMMAND.equals(userEdit.getText().toString())) {
                    ToastUtils.showShort("口令错误");
                    return;
                }

                listener.loginClick(isCommand, userEdit.getText().toString(), passwordEdit.getText().toString());

                dialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isCommand) {
                    changeCommand(false);
                } else {
                    cancel();
                }
            }
        });

        dialogview.findViewById(R.id.command).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCommand(true);
            }
        });

        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }


    private void changeCommand(boolean setCommand) {
        isCommand = setCommand;

        title.setText(isCommand ? "管理员登入" : "口令登入");
        passwordLayout.setVisibility(isCommand ? View.GONE : View.VISIBLE);
        commandText.setVisibility(isCommand ? View.GONE : View.VISIBLE);


    }

    private TextView syncTime;

    /**
     * 设置弹窗
     */
    public Dialog getSettingDialog(final Context context, final DialogOnClickListener listener) {

        cancel();

        View dialogview = View.inflate(context, R.layout.dialog_setting, null);

        title = dialogview.findViewById(R.id.dialog_title);

        commandText = dialogview.findViewById(R.id.command);

        passwordLayout = dialogview.findViewById(R.id.password_layout);

        userEdit = dialogview.findViewById(R.id.user_edit);

        userEdit.addTextChangedListener(textWatcher);

        passwordEdit = dialogview.findViewById(R.id.password_edit);

        passwordEdit.addTextChangedListener(textWatcher);

        TextView number_text = dialogview.findViewById(R.id.number_text);

        if (BaseConstant.MODE == BaseConstant.MODE_SCHOOL || BaseConstant.MODE == BaseConstant.MODE_SHOWSCHOOL) {
            number_text.setText("学校编号");
            passwordLayout.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(CacheUtils.get(context).getAsString(CacheUtils.SCHOOL_ID))) {
                userEdit.setText(CacheUtils.get(context).getAsString(CacheUtils.SCHOOL_ID));
            }

        } else if (BaseConstant.MODE == BaseConstant.MODE_DORM) {
            number_text.setText("宿舍编号");
            passwordLayout.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(CacheUtils.get(context).getAsString(CacheUtils.DORM_ID))) {
                userEdit.setText(CacheUtils.get(context).getAsString(CacheUtils.DORM_ID));
            }

        } else {
            number_text.setText("班级编号");
            passwordLayout.setVisibility(View.VISIBLE);

            if (!TextUtils.isEmpty(CacheUtils.get(context).getAsString(CacheUtils.CLASS_ID))) {
                userEdit.setText(CacheUtils.get(context).getAsString(CacheUtils.CLASS_ID));
            }

            if (!TextUtils.isEmpty(CacheUtils.get(context).getAsString(CacheUtils.CLASSROOM_ID))) {
                passwordEdit.setText(CacheUtils.get(context).getAsString(CacheUtils.CLASSROOM_ID));
            }

        }


        syncTime = dialogview.findViewById(R.id.last_synctime);

        setSyncTime(context);

        dialogview.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(userEdit.getText().toString())) {

                    if (BaseConstant.MODE == BaseConstant.MODE_SCHOOL || BaseConstant.MODE == BaseConstant.MODE_SHOWSCHOOL) {
                        ToastUtils.showShort("请输入学校编号");
                    } else if (BaseConstant.MODE == BaseConstant.MODE_DORM) {
                        ToastUtils.showShort("请输入宿舍编号");
                    } else {
                        ToastUtils.showShort("请输入班级编号");
                    }

                    return;
                }

                if ((BaseConstant.MODE == BaseConstant.MODE_CLASS || BaseConstant.MODE == BaseConstant.MODE_WELCOME || BaseConstant.MODE == BaseConstant.MODE_EXAM) && TextUtils.isEmpty(passwordEdit.getText().toString())) {
                    ToastUtils.showShort("请输入教室编号");
                    return;
                }


                listener.loginClick(isCommand, userEdit.getText().toString(), passwordEdit.getText().toString());


            }
        });

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        dialogview.findViewById(R.id.login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheUtils.get(context).remove(CacheUtils.UID);
                cancel();
            }
        });


        dialogview.findViewById(R.id.sync_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.syncClick();
//                dialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);

                dialog.dismiss();
            }
        });

        dialogview.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.cancelClick();
                }
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 2));

        return dialog;
    }

    public void setSyncTime(Context context) {

        if (syncTime != null && !TextUtils.isEmpty(CacheUtils.get(context).getAsString(CacheUtils.SYNC_TIME))) {
            long time = Long.parseLong(CacheUtils.get(context).getAsString(CacheUtils.SYNC_TIME));
            Date d = new Date(time);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            syncTime.setText("上次同步时间：" + sf.format(d));
        }

    }


    private int base_total = 100;//班级总分
    private int total = 0;//班级当前总分

    /**
     * 值日生评分弹窗
     */
    public Dialog getDutyDialog(Context context, final List<FormBean> list, String className, final AddScoreClickListener listener) {

        if (list == null || list.isEmpty()) {
            ToastUtils.showShort("没有数据");
            return null;
        }

        cancel();

        base_total = 100;//先默认一个数据
        total = 1;

        View dialogview = View.inflate(context, R.layout.dialog_checkin_duty, null);

        RecyclerView recyclerView = dialogview.findViewById(R.id.seekbar_recycleview);

        final SeekBar totalSeekBar = dialogview.findViewById(R.id.seekbar_class);

        final TextView scoreClass = dialogview.findViewById(R.id.score_class);

        TextView judgeClass = dialogview.findViewById(R.id.judge_class);

        judgeClass.setText("评分班级：" + className);

        MultiItemTypeAdapter seekbarItemAdapter = new MultiItemTypeAdapter(context, list);

        seekbarItemAdapter.addItemViewDelegate(new SeekbarItemView(context, new SeekbarItemView.SeekBarChangeListener() {
            @Override
            public void seekChnge(int position, int score) {

                if (list != null && list.size() > position) {

                    FormBean formBean = list.get(position);
                    total = total - formBean.getScore() + score;
                    formBean.setScore(score);
                    totalSeekBar.setProgress(total);
                    scoreClass.setText(String.valueOf(total));
                }

            }
        }));

        RecyclerViewAdapter seekbarAdapter = new RecyclerViewAdapter(seekbarItemAdapter);
        recyclerView.setAdapter(seekbarAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setTotal(list);

        totalSeekBar.setMax(base_total);
        totalSeekBar.setProgress(total);

        scoreClass.setText(String.valueOf(total));

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        dialogview.findViewById(R.id.command).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.addScore(setScore(list));
                }
            }
        });

        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 1.2f));

        return dialog;
    }

    private void setTotal(List<FormBean> list) {

        base_total = 0;
        total = 0;
        for (FormBean formBean : list) {
            base_total = base_total + formBean.getBase_score();
            total = total + formBean.getScore();
        }
    }

    private String setScore(List<FormBean> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (FormBean formBean : list) {
            stringBuilder.append("\"").append(formBean.getId()).append(":").append(formBean.getScore()).append("\"").append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /**
     * 个人信息弹窗
     */
    public Dialog getStudentMsgDialog(final Context context, ScoreBean scoreBean, final DialogOnClickListener listener) {

        cancel();

        View dialogview = View.inflate(context, R.layout.dialog_normal_studentmsg, null);

        RecyclerView recyclerView = dialogview.findViewById(R.id.score_recycleview);

        MultiItemTypeAdapter scoreItemAdapter = new MultiItemTypeAdapter(context, scoreBean.getStudentWeekScoreBean().getType_score());

        scoreItemAdapter.addItemViewDelegate(new ScoreItemView(context));

        RecyclerViewAdapter scoreAdapter = new RecyclerViewAdapter(scoreItemAdapter);
        recyclerView.setAdapter(scoreAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (BaseConstant.MODE == BaseConstant.MODE_WELCOME) {
            dialogview.findViewById(R.id.get_timetable).setVisibility(View.GONE);
        } else {
            dialogview.findViewById(R.id.get_timetable).setVisibility(View.VISIBLE);
            dialogview.findViewById(R.id.get_timetable).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.syncClick();
                    dialog.dismiss();
                }
            });
        }


        if (scoreBean.getExam() != null && !scoreBean.getExam().isEmpty()) {

            ExamBean examBean = scoreBean.getExam().get(0);

            ((TextView) dialogview.findViewById(R.id.exam_name)).setText("考试名称：" + examBean.getExam_name());
            ((TextView) dialogview.findViewById(R.id.exam_subject)).setText("科目：" + examBean.getSubject_name());
            ((TextView) dialogview.findViewById(R.id.exam_score)).setText("成绩：" + examBean.getScore());
            ((TextView) dialogview.findViewById(R.id.exam_classrang)).setText("班排名：" + examBean.getClass_range());
            ((TextView) dialogview.findViewById(R.id.exam_schoolscore)).setText("级排名：" + examBean.getTotal_range());
        }


        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 1.5f));

        return dialog;
    }

    /**
     * 签到簿弹窗
     */
    public Dialog getSignBookDialog(final Context context, ClockDetailBean clockDetailBean) {

        cancel();

        View dialogview = View.inflate(context, R.layout.dialog_signbook, null);

        RecyclerView recyclerView = dialogview.findViewById(R.id.sign_recycleview);


        MultiItemTypeAdapter signbookItemAdapter = new MultiItemTypeAdapter(context, clockDetailBean.getClock_list());

        signbookItemAdapter.addItemViewDelegate(new SignBookItemView(context));

        RecyclerViewAdapter signbookAdapter = new RecyclerViewAdapter(signbookItemAdapter);
        recyclerView.setAdapter(signbookAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 6);
        recyclerView.setLayoutManager(gridLayoutManager);

        if (clockDetailBean.getClock_count() != null) {

            ((TextView)dialogview.findViewById(R.id.total_number)).setText(String.format(context.getString(R.string.check_on_total), clockDetailBean.getClock_count().getTotal_student()));
            ((TextView)dialogview.findViewById(R.id.attendence_number)).setText(String.format(context.getString(R.string.check_on_clock), clockDetailBean.getClock_count().getClock_student()));
            ((TextView)dialogview.findViewById(R.id.leave_number)).setText(String.format(context.getString(R.string.check_on_leave), clockDetailBean.getClock_count().getLeave_student()));
            ((TextView)dialogview.findViewById(R.id.late_number)).setText(String.format(context.getString(R.string.check_on_late), clockDetailBean.getClock_count().getLate_student()));
            ((TextView)dialogview.findViewById(R.id.no_number)).setText(String.format(context.getString(R.string.check_on_nocome), clockDetailBean.getClock_count().getTotal_student() - clockDetailBean.getClock_count().getClock_student()));
        }

        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 1.5f));

        return dialog;
    }

    /**
     * 作业弹窗
     */
    public Dialog getHomeWorkDialog(final Context context, final HomeWorkBean homeWorkBean) {

        cancel();

        if (homeWorkBean.getImage() == null) {

            List<ImageBean> imageBeanList = new ArrayList<>();

            imageBeanList.add(new ImageBean(""));

            homeWorkBean.setImage(imageBeanList);
        }

        View dialogview = View.inflate(context, R.layout.dialog_homework, null);

        TextView subject = dialogview.findViewById(R.id.subject);
        TextView homeworkName = dialogview.findViewById(R.id.homework_name);

        subject.setText(homeWorkBean.getSubject());
        homeworkName.setText(homeWorkBean.getTitle());


        HomeWorkViewPagerAdapter homeWorkViewPagerAdapter = new HomeWorkViewPagerAdapter(context, homeWorkBean.getImage());
        final ViewPager viewPager = dialogview.findViewById(R.id.image_viewpager);
        viewPager.setAdapter(homeWorkViewPagerAdapter);

        FrameLayout webviewLayout = dialogview.findViewById(R.id.webview_layout);

        webviewLayout.removeAllViews();
        WebView mWebView = new WebView(context);
        webviewLayout.addView(mWebView);

        WebSettings settings = mWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //字体
        settings.setLoadsImagesAutomatically(true);
        settings.setTextZoom(120);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);

        //"http://sku-market-gw.jd.com"
        mWebView.loadDataWithBaseURL(null, homeWorkBean.getContent(), "text/html", "utf-8", null);


        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ImageView left = dialogview.findViewById(R.id.left);
        ImageView right = dialogview.findViewById(R.id.right);

        if (homeWorkBean.getImage().size() < 2) {
            left.setVisibility(View.GONE);
            right.setVisibility(View.GONE);
        }

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() > 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < homeWorkBean.getImage().size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });


        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 2f));

        return dialog;
    }

    /**
     * 校园通知弹窗
     */
    public Dialog getSchoolNoticeDialog(final Context context, final MessageBean messageBean) {

        cancel();

        View dialogview = View.inflate(context, R.layout.dialog_school_notice, null);

        TextView title = dialogview.findViewById(R.id.notice_title);

        title.setText(messageBean.getMessage_title());

        FrameLayout webviewLayout = dialogview.findViewById(R.id.webview_layout);

        webviewLayout.removeAllViews();
        WebView mWebView = new WebView(context);
        webviewLayout.addView(mWebView);

        WebSettings settings = mWebView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //字体
        settings.setLoadsImagesAutomatically(true);
        settings.setTextZoom(120);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);

        //"http://sku-market-gw.jd.com"
        mWebView.loadDataWithBaseURL(null, messageBean.getMessage_content(), "text/html", "utf-8", null);


        dialogview.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog = new Dialog(context, R.style.loaddialog);

        showDialog(context, dialogview, getWidth(context, 2f));

        return dialog;
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (handler != null) {
                handler.removeMessages(1);
                handler.sendEmptyMessageDelayed(1, 1000 * 10);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    public interface DialogOnClickListener {


        void loginClick(boolean isCommand, String username, String password);

        void cancelClick();

        void syncClick();
    }

    public interface AddScoreClickListener {
        void addScore(String score);
    }


}
