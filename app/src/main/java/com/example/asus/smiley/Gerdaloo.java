package com.example.asus.smiley;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Gerdaloo extends RelativeLayout {
    String TAG = "Smiley";
    private int size;
    EmotionalFaceView emotionalFaceView;
    InvCircle[] circles = new InvCircle[30];
    float[] tetas = new float[30];

    public Gerdaloo(Context context) {
        super(context);
        init(context);
    }

    public Gerdaloo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public Gerdaloo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float loc = (size * 0.5f - x) * (size * 0.5f - x) + (size * 0.5f - y) * (size * 0.5f - y);
        float max = (size * 0.45f) * (size * 0.45f);
        float min = (size * 0.35f) * (size * 0.35f);
        return loc >= min && loc <= max;

    }


    //TODO attend warning bellow
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(getContext(), "Touched", Toast.LENGTH_SHORT).show();
        float x = event.getX();
        float y = event.getY();
        float r = size * 0.4f;
        float ax = size * 0.5f;
        int n = 30;
        float ay = ax;
        float dis = (float) (Math.sqrt((x - ax) * (x - ax) + (y - ay) * (y - ay)));
        float cx = ax + r * ((x - ax) / dis);
        float cy = ay + r * ((y - ay) / dis);

        float cos = (ax - cx) / r;
        float sin = (ay - cy) / r;
        int state = 0;
        float min = (sin - (float) Math.sin(tetas[0]))*(sin - (float) Math.sin(tetas[0])) + (cos- (float) Math.cos(tetas[0]))*(cos- (float) Math.cos(tetas[0]));
        for (int i = 0; i < n; i++) {
            float sinT = (float) Math.sin(tetas[i]);
            float cosT = (float) Math.cos(tetas[i]);
            float shit = (sin - sinT)*(sin - sinT) * (cos - cosT)*(cos - cosT);
            if (min > shit) {
                min = shit;
                state = i;
            }

        }
        float currentTeta = sin / cos;
        Log.d(TAG, "onTouchEvent: " + state);


        circles[1].setAttrs(cx, cy, size * 0.08f);
        circles[1].invalidate();
        return true;
    }

    private void init(final Context context) {

        circles[0] = new InvCircle(context, null, 1);
        circles[1] = new InvCircle(context, null, 2);
        addView(circles[0]);
        addView(circles[1]);
        emotionalFaceView = new EmotionalFaceView(context, null);
        addView(emotionalFaceView);
        emotionalFaceView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        for (int i = 0; i < 30; i++) {
//            circles[i] = new InvCircle(context, null, i);
//            circles[i].setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            addView(circles[i]);
//            final int finalI = i;
//            Log.d(TAG, "init: " + finalI);
////            circles[i].setOnClickListener();
//        }
//        Log.d(TAG, "init: here " + size);
        for (int i = 0; i < 30; i++) {
            tetas[i] = (float) (i * (2 * Math.PI / 30));

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        emotionalFaceView.setSize(size);
        circles[0].setAttrs(size * 0.5f, size * 0.5f, size * 0.35f);
        circles[1].setAttrs(size * 0.2995f, size * 0.154f, size * 0.08f);
        circles[1].setColor(Color.RED);
//        int cnt = 0;
//        for (double i = Math.PI / 6 - Math.PI / 2; i < (2 * Math.PI - Math.PI / 6 - Math.PI / 2) - Math.PI / 18; i += Math.PI / 18) {
//            float x = (float) (size * 0.5f + size * 0.45f * Math.cos(i));
//            float y = (float) (size * 0.5f + size * 0.45f * Math.sin(i));
//            if (cnt < 30) {
//                circles[cnt].setAttrs(x, y, size * 0.05f);
//
//                cnt++;
//            }
//            Log.d(TAG, "drawShit: " + i + " , " + x + " , " + y + " , " + size);
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        size = Math.min(getMeasuredHeight(), getMeasuredWidth());
        Log.d(TAG, "onMeasure: " + size);
        // 2
        setMeasuredDimension(size, size);
    }

}
