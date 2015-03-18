package com.philheenan.bignews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class FontDisplaySurfaceView extends SurfaceView {

	public FontDisplaySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		update();
		setWillNotDraw(false);
	}

	public FontDisplaySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		update();
		setWillNotDraw(false);
	}

	public FontDisplaySurfaceView(Context context) {
		super(context);
		update();
		setWillNotDraw(false);
	}

	String mText = "";

	public void setText(String aText) {
		mText = aText;
		update();
	}

	int mDrawX = 100;
	int mDrawY = 100;

	Typeface mTextTypeface = Typeface.DEFAULT;

	public void setTextTypeface(Typeface aTypeface) {
		mTextTypeface = aTypeface;
		update();
	}

	public int mHinting = Paint.HINTING_OFF;

	public Align mAlign = Align.LEFT;

	Paint mTextPaint = new Paint();
	Rect mTextRect = new Rect();
	Rect mTextBounds =  new Rect();
	float mTextMeasure;
	FontMetrics mFontMetrics;
	Paint mBoundPaint = new Paint();

	public void update() {
		mTextPaint.reset();
		mTextPaint.setTypeface(mTextTypeface);
		mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.abc_text_size_display_1_material));
		mTextPaint.setTextAlign(mAlign);
		mTextPaint.setHinting(mHinting);
		mTextPaint.setColor(Color.BLACK);
		mTextPaint.setStyle(Paint.Style.FILL);
		mFontMetrics = mTextPaint.getFontMetrics();

		mBoundPaint.setColor(getResources().getColor(R.color.highlight));
		mBoundPaint.setStyle(Paint.Style.FILL);
		
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
		mTextRect.offset(mDrawX, mDrawY);
		mTextMeasure = mTextPaint.measureText(mText);

		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);

        int height = mTextRect.bottom - mTextRect.top;
        int width = mTextRect.right - mTextRect.left;
        System.out.println("########## height: " + height);
        System.out.println("########## width: " + width);

        mTextRect.bottom = mTextRect.bottom + (height / 10);
        mTextRect.top = mTextRect.top - (height / 5);
        mTextRect.left = mTextRect.left - (width / 100);
        mTextRect.right = mTextRect.right + (width / 100);

		canvas.drawRect(mTextRect, mBoundPaint);
		canvas.drawText(mText, mDrawX, mDrawY, mTextPaint);

	}

}
