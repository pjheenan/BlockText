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

    /**
     * <dimen name="abc_text_size_body_1_material">16sp</dimen>
     *
     <dimen name="abc_text_size_body_2_material">16sp</dimen>

     <dimen name="abc_text_size_button_material">14sp</dimen>

     <dimen name="abc_text_size_caption_material">12sp</dimen>

     <dimen name="abc_text_size_display_1_material">34sp</dimen>

     <dimen name="abc_text_size_display_2_material">45sp</dimen>

     <dimen name="abc_text_size_display_3_material">56sp</dimen>

     <dimen name="abc_text_size_display_4_material">112sp</dimen>

     <dimen name="abc_text_size_headline_material">24sp</dimen>

     <dimen name="abc_text_size_large_material">22sp</dimen>

     <dimen name="abc_text_size_medium_material">18sp</dimen>

     <dimen name="abc_text_size_menu_material">16sp</dimen>

     <dimen name="abc_text_size_small_material">14sp</dimen>

     <dimen name="abc_text_size_subhead_material">16sp</dimen>

     <dimen name="abc_text_size_subtitle_material_toolbar">16dp</dimen>

     <dimen name="abc_text_size_title_material">20sp</dimen>

     <dimen name="abc_text_size_title_material_toolbar">20dp</dimen>
     *
     */

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
    int paddingMultiLineTop;
    float baseTextSize = 135f;

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
        paddingMultiLineTop = (int)(padding * (2.5f / (baseTextSize / getTextSize())));
        padding25x = (int)(padding * 2.5f);
        paddingRow1Bottom = (int)(padding * (4.5 / (baseTextSize / getTextSize())));
        paddingNotRow1Bottom = padding * 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("text size: " + getTextSize());
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        System.out.println("font metrics: "
                + "- ascent: " + metrics.ascent
                + " - descent: " + metrics.descent
                + " - leading: " + metrics.leading
                + " - top: " + metrics.top
                + " - bottom: " + metrics.bottom);
        System.out.println("baseline: " + getBaseline());

        count = getLineCount();

        for (int j = 0; j < getText().length(); j++) {
            array.put(getLayout().getLineForOffset(j), j);
        }
        for (int i = 0; i < count; i++) {
            getLineBounds(i, bgRect);
            bgRect.left -= padding;
            System.out.println("base rect: " + bgRect.toString());
            System.out.println("bottom padding: " + getPaddingBottom());
            System.out.println("top padding: " + getPaddingTop());
            System.out.println("line height: " + getLineHeight());
            System.out.println("compound bottom padding: " + getCompoundPaddingBottom());
            System.out.println("compound top padding: " + getCompoundPaddingTop());

            bgRect.right = (int) getLayout().getSecondaryHorizontal(array.get(i) + (i == (count - 1) ? 1 : 0)) + padding25x;

//            bgRect.bottom -= metrics.descent;
            if (i < (count -1)) {
                bgRect.bottom -= metrics.leading;
            }
//            if (i > 0) {
//                bgRect.top -= metrics.bottom;
//            }

//            if (i == 0) {
                bgRect.top = (int)(bgRect.bottom + metrics.ascent) - getPaddingTop() - (int)metrics.descent - (int)(metrics.leading / 2);
//                bgRect.bottom -= (count > 1) ? paddingRow1Bottom : paddingSingleRowBottom;
//            } else {
//                bgRect.top -= paddingMultiLineTop;
                if (count > 1 && i < (count -1)) {
//                    bgRect.bottom -= paddingNotRow1Bottom;
                }
//            }
            System.out.println("modified rect: " + bgRect.toString());
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
