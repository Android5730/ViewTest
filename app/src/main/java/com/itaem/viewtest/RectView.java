package com.itaem.viewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RectView extends View {
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int  mColor = Color.RED;
    public RectView(Context context) {
        super(context);
        initDraw();
    }
    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 获取View的样式属性信息，即属性集合
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        // 提取RectView属性集合的rect_color 属性，如果没设置，默认值为Color.RED
        mColor = mTypedArray.getColor(R.styleable.RectView_rect_color,Color.RED);
        // 获取资源后要及时回收
        mTypedArray.recycle();
        initDraw();
    }
    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDraw();
    }
    private void initDraw(){
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(1.5F);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取四个方向的padding
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        // 高度减去上下两个padding
        int height = getHeight() - paddingBottom - paddingTop;
        // 宽度减去左右两个padding
        int width = getWidth() - paddingLeft - paddingRight;
        // 画出大小，非定位
        // 右边和下边是距离父容器的右边和下边的距离
        // 宽高没错 width+paddingRight-paddingLeft = width
        canvas.drawRect(paddingLeft,paddingTop,width+paddingRight,height+paddingBottom,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取测量模式
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        // 最大模式，wrap_content
        // 如果宽高都是这个模式，一起设置宽高默认值
        if (widthSpecMode == MeasureSpec.AT_MOST&&heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(600,600);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){
            // 设置宽默认值
            setMeasuredDimension(600,heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){
            // 设置高默认值
            setMeasuredDimension(widthSpecSize,600);
        }
    }
}
