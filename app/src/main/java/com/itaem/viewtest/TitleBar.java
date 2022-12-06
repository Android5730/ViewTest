package com.itaem.viewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleBar extends RelativeLayout {
    private ImageView iv_titlebar_left;
    private TextView tv_titlebar_title;
    private ImageView iv_titlebar_right;
    private RelativeLayout layout_titlebar_rootLayout;
    private int mColor = Color.BLUE;
    private int mTextColor = Color.WHITE;
    private float textSize = 15F;
    private String text = null;
    public TitleBar(Context context) {
        super(context);
        initView(context);
    }
    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs,R.styleable.TitleBar);
        mColor = mTypeArray.getColor(R.styleable.TitleBar_title_bg,Color.BLUE);
        mTextColor = mTypeArray.getColor(R.styleable.TitleBar_title_text_color,Color.WHITE);
        textSize =  mTypeArray.getDimension(R.styleable.TitleBar_title_text_size,15F);
        text = mTypeArray.getString(R.styleable.TitleBar_title_text);
        initView(context);
        initClick();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.toolbar_my,this,true);
        iv_titlebar_left = findViewById(R.id.iv_titlebar_left);
        iv_titlebar_right = findViewById(R.id.iv_titlebar_right);
        tv_titlebar_title = findViewById(R.id.tv_titlebar_title);
        layout_titlebar_rootLayout = findViewById(R.id.layout_titlebar_rootLayout);
        // 设置背景颜色
        layout_titlebar_rootLayout.setBackgroundColor(mColor);
        // 设置标题文字颜色
        tv_titlebar_title.setTextColor(mTextColor);
        // 设置文字
        tv_titlebar_title.setText(text);
        tv_titlebar_title.setTextSize(textSize);
    }
    // 回调终曲
    public void initClick(){
        iv_titlebar_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iconOnClickListener!=null){
                    iconOnClickListener.onLeftListener();
                }
            }
        });
        iv_titlebar_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iconOnClickListener!=null) {
                    iconOnClickListener.onRightListener();
                }
            }
        });
    }
    // 接收点击事件的接口对象
    private IconOnClickListener iconOnClickListener;
    // 暴露外部的点击方法
    public void setIconOnClickListener(IconOnClickListener iconOnClickListener) {
        this.iconOnClickListener = iconOnClickListener;
    }
    // 内部接口类。设置点击方法
    public interface IconOnClickListener{
        void onLeftListener();
        void onRightListener();
    }
}
