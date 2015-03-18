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
    int paddingSingleRowBottom;
    int paddingNotRow1Bottom;
    int padding25x;
    int paddingRow1Bottom;

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
//        padding5x = padding * 5;
        paddingSingleRowBottom = padding / 2;
        padding25x = (int)(padding * 2.5f);
        paddingRow1Bottom = (int)(padding * 4.5);
        paddingNotRow1Bottom = padding * 4;
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
                bgRect.bottom -= (count > 1) ? paddingRow1Bottom : paddingSingleRowBottom;
            } else {
                bgRect.top -= padding25x;
                if (count > 1 && i < (count -1)) {
                    bgRect.bottom -= paddingNotRow1Bottom;
                }
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
