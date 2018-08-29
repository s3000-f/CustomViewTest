package com.example.asus.smiley;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class InvCircle extends View {
    int id;
    public float x, y, r;
    int color = Color.GREEN;
    String TAG = "Smiley";

    public InvCircle(Context context, @Nullable AttributeSet attrs, int id) {
        super(context, attrs);
        this.id = id;
    }

    public InvCircle(Context context, @Nullable AttributeSet attrs, float x, float y, float r,int id) {
        this(context, attrs,id);
        this.r = r;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        Log.d(TAG, "onDraw: minicircles");
        canvas.drawCircle(x, y, r, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(getContext(),"Circle",Toast.LENGTH_SHORT).show();
        return true;
    }

    public void setAttrs(float x, float y, float r) {
        this.r = r;
        this.x = x;
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
