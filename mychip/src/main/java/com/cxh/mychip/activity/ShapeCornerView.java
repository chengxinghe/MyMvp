package com.cxh.mychip.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * ShapeCornerView
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @author Chengxinghe
 * @version 1.0
 * @date 2018/7/30 18:35
 * @seecom.air.airmessage
 */
public class ShapeCornerView extends View {
    private RectF mRectF;

    public ShapeCornerView(Context context) {
        super(context);
    }

    public ShapeCornerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeCornerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width > height) {
            width = height;
        } else {
            height = width;
        }
        setMeasuredDimension(width, height);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getMeasuredWidth() / 2;
        int centerLeft = getLeft() + radius;
        int centerTop = getTop() + radius;
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawCircle(centerLeft,centerTop,radius,mPaint);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = size;
                break;
            default:
                break;
        }
        return result;
    }


}
