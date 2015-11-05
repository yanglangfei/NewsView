package com.ylf.jucaipen.newsview.com.ylf.jucaipen.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.OutputStream;

/**
 * Created by Administrator on 2015/11/5.
 */
public class MyPainBoard extends View {

    private Paint mPaint = null;
    private Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;
    private float startX;
    private float startY ;
    private float stopY;
    private float stopX;
    private  int mScreenWidth;
    private  int mScreenHeight;

    public MyPainBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth=wm.getDefaultDisplay().getWidth();
        mScreenHeight=wm.getDefaultDisplay().getHeight();
        mBitmap = Bitmap.createBitmap(mScreenWidth,mScreenHeight, Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
        mBitmapCanvas.drawColor(Color.GRAY);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
    }

    public MyPainBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPainBoard(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                break;
            case  MotionEvent.ACTION_MOVE:
                stopX=event.getX();
                stopY=event.getY();
                mBitmapCanvas.drawLine(startX,startY,stopX,stopY,mPaint);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap!=null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }

    public void saveBitmap(OutputStream stream) {
        if (mBitmap != null) {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
    }
}
