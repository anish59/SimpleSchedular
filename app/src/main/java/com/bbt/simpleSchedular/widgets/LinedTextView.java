package com.bbt.simpleSchedular.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class LinedTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    private Rect mRect;
    private Paint mPaint;

    // we need this constructor for LayoutInflater
    public LinedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int line_height = getLineHeight();

        int count = height / line_height;

        if (getLineCount() > count)
            count = getLineCount();

        Rect r = mRect;
        Paint paint = mPaint;
        int baseline = getLineBounds(0, r);

        for (int i = 0; i < count; i++) {

            canvas.drawLine(r.left, baseline + 2, r.right, baseline + 2, paint);
            baseline += getLineHeight();
        }
        super.onDraw(canvas);

    }
/*
//    xml view
     <com.bbt.simpleSchedular.widgets.LinedEditText
    android:id="@+id/edit_story"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="6dp"
    android:background="@null"
    android:inputType="textMultiLine|textNoSuggestions"
    android:minLines="10"
    android:gravity="top|left"
    android:singleLine="false"
    android:imeOptions="actionNone"
    android:text="Story : \n"  />

*/
}