package com.itaem.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
public class CustomView extends View {
    private int lastX;
    private int lastY;
    private Scroller scroller;

    public CustomView(Context context) {
        super(context);
        // 初始化
        scroller = new Scroller(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取触摸的位置（相对于自定义View）
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            // 获取触摸点的坐标
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            // 计算偏移量
            case MotionEvent.ACTION_MOVE:
                // 计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // 特点 参数为：负值
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
        }
        // 改为true，把事件消费，才有移动效果
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
        @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }
    public void smoothScrollTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        scroller.startScroll(scrollX,0,delta,0,2000);
        invalidate();
    }
}

