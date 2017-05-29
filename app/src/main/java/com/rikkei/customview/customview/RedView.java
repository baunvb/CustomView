package com.rikkei.customview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Bau NV on 5/29/2017.
 */

public class RedView extends View {
    private int mDefaultTextSize = 20;
    private int bgColor = Color.RED;
    private Paint mPaint;
    private Paint mTestPaint;
    private String mMessage = "Android";

    public RedView(Context context) {
        super(context);
        initPaints();
    }

    public RedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public RedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(bgColor);
        mPaint.setTextSize(mDefaultTextSize);

        mTestPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTestPaint.setStyle(Paint.Style.FILL);
        mTestPaint.setColor(bgColor);
        mTestPaint.setTextSize(mDefaultTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        int textWidth =
                width - getPaddingLeft() - getPaddingRight();
        int textHeight =
                height - getPaddingTop() - getPaddingBottom();
        adjustTextSizeToFit(Math.min(textWidth, textHeight));
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        return (measureText(measureSpec, getPaddingLeft(),
                getPaddingRight()));
    }

    private int measureHeight(int measureSpec) {
        return (measureText(measureSpec, getPaddingTop(),
                getPaddingBottom()));
    }

    private int measureText(int measureSpec,
                            int padding1, int padding2) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 2 * (int) mPaint.measureText(mMessage) +
                    padding1 + padding2;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return (result);
    }

    private void adjustTextSizeToFit(int availableLength) {
        int textSize = 0;
        for(int i=1; i<=10; i++) {
            textSize = i * 10;
            mTestPaint.setTextSize(textSize);
            int requiredLength =
                    2 * (int)mTestPaint.measureText(mMessage);
            if (requiredLength > availableLength) {
                break;
            }
        }
        mPaint.setTextSize(Math.max(10, textSize - 10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        canvas.drawRect(0, 0, viewWidth, viewHeight, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setBackgroundColor(Color.YELLOW);
        return true;
    }

}
