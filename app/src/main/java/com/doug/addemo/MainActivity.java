/*
 * Copyright (c) 2018. doug.
 */

package com.doug.addemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.doug.adlib.AdConstant;
import com.doug.adlib.AdManager;
import com.doug.adlib.alertview.AlertView;
import com.doug.adlib.alertview.OnDismissListener;
import com.doug.adlib.alertview.OnItemClickListener;
import com.doug.adlib.bean.AdInfo;
import com.doug.adlib.transformer.DepthPageTransformer;
import com.doug.adlib.transformer.RotateDownPageTransformer;
import com.doug.adlib.view.AdCountView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdCountView.OnStatusChangeListener, View.OnClickListener,OnItemClickListener, OnDismissListener {

    private List<AdInfo> advList = null;
    private Spinner spinner = null;
    private Button button1 = null;
    private EditText editText = null;
    private Button button2 = null;
    private Button button3 = null;
    private Button button4 = null;
    private Button button5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

        initAdCountView();

        initAlert();

        initListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        advList = new ArrayList<>();
        AdInfo adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage1.png");
        advList.add(adInfo);

        adInfo = new AdInfo();
        adInfo.setActivityImg("https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png");
        advList.add(adInfo);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        spinner = (Spinner) findViewById(R.id.spinner);
        List<DataBean> mList = new ArrayList<>();
        mList.add(new DataBean(-1, "请选择广告弹窗动画类型"));
        mList.add(new DataBean(AdConstant.ANIM_DOWN_TO_UP, "从下至上弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_UP_TO_DOWN, "从上至下弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_LEFT_TO_RIGHT, "从左至右弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_RIGHT_TO_LEFT, "从右至左弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_UPLEFT_TO_CENTER, "从左上弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_UPRIGHT_TO_CENTER, "从右上弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_DOWNLEFT_TO_CENTER, "从左下弹出广告弹窗"));
        mList.add(new DataBean(AdConstant.ANIM_DOWNRIGHT_TO_CENTER, "从右下弹出广告弹窗"));
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(mList, this);
        spinner.setAdapter(spinnerAdapter);


        editText = (EditText) findViewById(R.id.edittext);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

    }

    /**
     * 初始化事件监听
     */
    private void initListener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AdManager adManager = new AdManager(MainActivity.this, advList);
                adManager.setOverScreen(true)
                        .setPageTransformer(new DepthPageTransformer());
                switch (position) {
                    /**
                     * 从下至上弹出广告弹窗
                     */
                    case 1:
                        adManager.showAdDialog(AdConstant.ANIM_DOWN_TO_UP);
                        break;
                    /**
                     * 从上至下弹出广告弹窗
                     */
                    case 2:
                        adManager.showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
                        break;
                    /**
                     * 从左向右弹窗广告弹窗
                     */
                    case 3:
                        adManager.showAdDialog(AdConstant.ANIM_LEFT_TO_RIGHT);
                        break;
                    /**
                     * 从右向左弹出广告弹窗
                     */
                    case 4:
                        adManager.showAdDialog(AdConstant.ANIM_RIGHT_TO_LEFT);
                        break;
                    /**
                     * 从左上弹出广告弹窗
                     */
                    case 5:
                        adManager.showAdDialog(AdConstant.ANIM_UPLEFT_TO_CENTER);
                        break;
                    /**
                     * 从右上弹出广告弹窗
                     */
                    case 6:
                        adManager.showAdDialog(AdConstant.ANIM_UPRIGHT_TO_CENTER);
                        break;
                    /**
                     * 从左下弹窗广告弹窗
                     */
                    case 7:
                        adManager.showAdDialog(AdConstant.ANIM_DOWNLEFT_TO_CENTER);
                        break;
                    /**
                     * 从右下弹出广告弹窗
                     */
                    case 8:
                        adManager.showAdDialog(AdConstant.ANIM_DOWNRIGHT_TO_CENTER);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity.this, advList);
                String result = editText.getText().toString();
                if (TextUtils.isEmpty(result)) {
                    Toast.makeText(MainActivity.this, "请输入弹出动画的角度!", Toast.LENGTH_SHORT).show();
                    return;
                }

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                });
                adManager.showAdDialog(Integer.parseInt(result));
            }
        });

        /**
         * 自定义设置广告活动弹窗距离屏幕两侧距离以及宽高比
         */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPadding(100)
                        .setWidthPerHeight(0.5f)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * 自定义弹窗背景颜色,弹窗是否覆盖全屏,关闭按钮是否显示等
         */
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setBackViewColor(Color.parseColor("#AA333333"))
                        .setDialogCloseable(false)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * 自定义设定弹窗弹性参数和速度参数
         */
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setBounciness(20)
                        .setSpeed(4)
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });

        /**
         * 自定义设置弹窗ViewPager滑动动画
         */
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdManager adManager = new AdManager(MainActivity.this, advList);

                adManager.setOnImageClickListener(new AdManager.OnImageClickListener() {
                    @Override
                    public void onImageClick(View view, AdInfo advInfo) {
                        Toast.makeText(MainActivity.this, "您点击了ViewPagerItem...", Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPageTransformer(new RotateDownPageTransformer())
                        .showAdDialog(AdConstant.ANIM_UP_TO_DOWN);
            }
        });
    }

    private void initAdCountView(){
        init1();
        init2();
        init3();
    }

    private AdCountView adCountView1;

    private AdCountView adcountview2;
    private AdCountView adcountview3;

    private void init3() {
        adcountview3 = (AdCountView) findViewById(R.id.ad_count_view3);
        adcountview3.setBackgroundColor(getResources().getColor(R.color.base_black_30));
        adcountview3.setOnClickListener(this);
        adcountview3.setOnStatusChangeListener(this);
        adcountview3.setOutRingColor(getResources().getColor(R.color.lightseagreen));
        adcountview3.setTextColor(getResources().getColor(R.color.base_white_100));
        adcountview3.setText("跳过广告");
        adcountview3.setTextSize(15);
        adcountview3.setInverseAnim(true);
        adcountview3.start();
    }

    private void init2() {
        adcountview2 = (AdCountView) findViewById(R.id.ad_count_view2);
        adcountview2.setBackgroundColor(getResources().getColor(R.color.base_white_80));
        adcountview2.setOutRingColor(getResources().getColor(R.color.colorAccent));
        adcountview2.setTextColor(getResources().getColor(R.color.base_black_100));
        adcountview2.setText("进入应用");
        adcountview2.start();
    }

    private void init1() {
        adCountView1 = (AdCountView) findViewById(R.id.ad_count_view1);
        adCountView1.start();
    }

    @Override
    public void onCountViewStart() {
        Toast.makeText(this, "动画开始", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountViewStop() {
        Toast.makeText(this, "动画结束", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "被点击", Toast.LENGTH_SHORT).show();
    }


    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;

    private void initAlert(){
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mAlertView = new AlertView("标题", "内容", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        //拓展窗口
        mAlertViewExt = new AlertView("提示", "请完善你的个人资料！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen=imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen&&focus ? 120 :0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    public void alertShow1(View view) {
        mAlertView.show();
    }

    public void alertShow2(View view) {
        new AlertView("标题", "内容", null, new String[]{"确定"}, null, this, AlertView.Style.Alert, this).show();
    }

    public void alertShow3(View view) {
        new AlertView(null, null, null, new String[]{"高亮按钮1", "高亮按钮2", "高亮按钮3"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3", "其他按钮4", "其他按钮5", "其他按钮6",
                        "其他按钮7", "其他按钮8", "其他按钮9", "其他按钮10", "其他按钮11", "其他按钮12"},
                this, AlertView.Style.Alert, this).show();
    }

    public void alertShow4(View view) {
        new AlertView("标题", null, "取消", new String[]{"高亮按钮1"}, new String[]{"其他按钮1", "其他按钮2", "其他按钮3"}, this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShow5(View view) {
        new AlertView("标题", "内容", "取消", null, null, this, AlertView.Style.ActionSheet, this).setCancelable(true).show();
    }

    public void alertShow6(View view) {
        new AlertView("上传头像", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }
    private void closeKeyboard() {
        //关闭软键盘
        imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
        //恢复位置
        mAlertViewExt.setMarginBottom(0);
    }
    @Override
    public void onItemClick(Object o,int position) {
        closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
            String name = etName.getText().toString();
            if(name.isEmpty()){
                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
            }

            return;
        }
        Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "消失了", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(mAlertView!=null && mAlertView.isShowing()){
                mAlertView.dismiss();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}
