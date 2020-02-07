package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class mCustomView extends View {

    private static final int SQUARE_SIZE = 100;
    private Rect mRectQuare;
    private Paint mPaintQuare;

    int mQuareColor, mQuareSize;

    private Paint mPaintCircle;

    public mCustomView(Context context) {
        super(context);
        init(null);
    }

    public mCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public mCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public mCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set) {
        mRectQuare = new Rect();
        mPaintQuare = new Paint(Paint.ANTI_ALIAS_FLAG); //ANTI_ALIAS_FLAG la de hinh anh khong bi nhoe

        mPaintCircle = new Paint();
        mPaintQuare.setAntiAlias(true); //giong new Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintCircle.setColor(Color.RED);

        TypedArray typedArray = getContext().obtainStyledAttributes(set, R.styleable.mCustomView);

        mQuareColor = typedArray.getColor(R.styleable.mCustomView_square_color, Color.GREEN); //mau mac dinh la GREEN
        mQuareSize = typedArray.getDimensionPixelSize(R.styleable.mCustomView_square_size, SQUARE_SIZE);

        mPaintQuare.setColor(mQuareColor);

        typedArray.recycle();

    }

    //ve view
    //ham nay se duoc goi nhieu lan
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //ve hinh vuong
        mRectQuare.top = 10;
        mRectQuare.left = 20;
        mRectQuare.bottom = mRectQuare.top + mQuareSize;
        mRectQuare.right = mRectQuare.left + mQuareSize;

       // canvas.drawRect(mRectQuare, mPaintQuare);

        //ve hinh tron
        float radius = 120f;
        float center_x = 100f;
        float center_y = 100f;


        canvas.drawCircle(center_x, center_y, radius, mPaintCircle);
    }
}
