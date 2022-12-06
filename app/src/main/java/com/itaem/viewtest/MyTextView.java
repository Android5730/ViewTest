package com.itaem.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {
    // 画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MyTextView(@NonNull Context context) {
        super(context);
        initDraw();
    }

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public MyTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }
    // 设置画笔
    private void initDraw(){
        // 设置颜色
        mPaint.setColor(Color.RED);
        // 设置描边的宽度
        mPaint.setStrokeWidth(1.5F);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0,height/2,width,height/2,mPaint);
    }
}
