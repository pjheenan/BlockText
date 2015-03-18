package com.philheenan.bignews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by phillip.heenan on 03/03/15.
 */
public class CooperTextView extends TextView {

    int bgColour;
    Rect bgRect = new Rect();
    Paint bgPaint = new Paint();
    SparseIntArray array = new SparseIntArray();
    int basePadding = 4;
    int padding = 12;
    int count;
    int padding5x;
    int paddingHalf;
    int padding25x;

    public CooperTextView(Context context) {
        super(context);
        presets();
    }

    public CooperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        presets();
    }

    public CooperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        presets();
    }

    public void setColour(int colour) {
        bgColour = colour;
        bgPaint.setColor(bgColour);
        bgPaint.setStyle(Paint.Style.FILL);
    }

    void presets() {
        padding = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, basePadding, getContext().getResources().getDisplayMetrics()));
        padding5x = padding * 5;
        paddingHalf = padding / 2;
        padding25x = (int)(padding * 2.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        count = getLineCount();
        for (int j = 0; j < getText().length(); j++) {
            array.put(getLayout().getLineForOffset(j), j);
        }
        for (int i = 0; i < count; i++) {
            getLineBounds(i, bgRect);
            bgRect.left -= padding;

            bgRect.right = (int) getLayout().getSecondaryHorizontal(array.get(i) + (i == (count - 1) ? 1 : 0)) + padding25x;

            if (i == 0) {
                bgRect.bottom -= count > 1 ? padding5x : paddingHalf;
            } else {
                bgRect.top -= padding25x;
            }
            canvas.drawRect(bgRect, bgPaint);
        }
        super.onDraw(canvas);
    }

    @Override
    public int getLineCount() {
        return super.getLineCount();
    }

    @Override
    public int getLineHeight() {
        return super.getLineHeight();
    }

    @Override
    public int getLineBounds(int line, Rect bounds) {
        return super.getLineBounds(line, bounds);
    }
}
