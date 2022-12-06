package com.itaem.viewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorzontaView extends ViewGroup {
    private int lastInterceptX;
    private int lastInterceptY;
    private int lastX;
    private int lastY;
    private int currentIndex = 0; // 当前子元素
    private int childWidth = 0;
    private Scroller scroller;

    private VelocityTracker tracker;// 测试滑动速度
    public HorzontaView(Context context) {
        super(context);
        init();
    }

    public HorzontaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorzontaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }
    // 为子元素定位
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0;i<childCount;i++){
            child = getChildAt(i);
            if (child.getVisibility()!=View.GONE){
                int width = child.getMeasuredWidth();
                // 赋值给他，以便左右滑动
                childWidth = width;
                child.layout(left,0,left+width,child.getMeasuredHeight());
                left+=width;
            }
        }
    }
    // 处理滑动冲突
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        // 获取点击事件的坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 滑动时已经变为true，置为false
                intercept = false;
                // 判断Scroller是否执行完成
                if (!scroller.isFinished()){
                    // 未完成用abortAnimation()打断Scroller
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算每次手指移动的距离
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                // 判断是水平滑动还是垂直滑动
                if (Math.abs(deltaX) - Math.abs(deltaY) >0){
                    // 设为true进行拦截
                    intercept = true;
                }else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }
    // 弹性滑动到其他页面 Scroller
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 判断Scroller是否执行完成
                if (!scroller.isFinished()){
                    // 未完成用abortAnimation()打断Scroller
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                // 判断滑动的距离是否大于宽度的 1/2 大于就切换
                if (Math.abs(distance)>childWidth/2){
                    if (distance>0){
                        currentIndex++;
                    }else {
                        currentIndex--;
                    }
                }else {
                    // 获取水平方向的速度
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    // 速度绝对值大于50，就认为快速滑动
                    if (Math.abs(xV)>50){
                        if (xV > 0){
                            currentIndex--;
                        }else {
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex<0?0:currentIndex>getChildCount()-1?getChildCount()-1:currentIndex;
                smoothScrollTo(currentIndex*childWidth,0);
                // 重置速度计算器
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(),getScrollY(),destX - getScrollX(),destY - getScrollY(),1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        if (getChildCount() == 0){
            setMeasuredDimension(0,0);
        }else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            // 每个宽度不同？
            setMeasuredDimension(childWidth*getChildCount(),childHeight);
        }else if (widthMode == MeasureSpec.AT_MOST){
            int childWidth = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(childWidth*getChildCount(),heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize,childHeight);
        }
    }

}
