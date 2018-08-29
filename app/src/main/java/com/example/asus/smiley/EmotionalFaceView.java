package com.example.asus.smiley;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class EmotionalFaceView extends View {


    String TAG = "Smiley";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int faceColor = Color.YELLOW;
    private int eyesColor = Color.BLACK;
    private int mouthColor = Color.BLACK;
    private int borderColor = Color.BLACK;
    private float borderWidth = 4.0f;
    private int size = 320;
    private Path mouthPath = new Path();
    private Path shitPath = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawFaceBackground(canvas);
//        drawEyes(canvas);
//        drawMouth(canvas);
        drawShit(canvas);
    }

    private void drawShit(Canvas canvas) {
        RectF oval = new RectF(size*0.05f, size*0.05f, size*0.95f, size*0.95f);
        RectF oval2 = new RectF(size * 0.15f, size * 0.15f, size * 0.85f, size * 0.85f);
        RectF cornerL = new RectF();
        RectF oval3 = new RectF(size * 0.2f, size * 0.2f, size * 0.9f, size * 0.9f);
        shitPath.addArc(oval, 300, 300);
        shitPath.addArc(oval2, 300, 300);
//        shitPath.addArc(oval3, 294, 350);
        shitPath.addArc(cornerL, getSemicircle(size * 0.274f, size * 0.111f, size * (0.325f), size * (0.197f), cornerL, 0), 180);
//        shitPath.moveTo(size * 0.225f, size * 0.007f);
//        shitPath.lineTo(size * 0.327f, size * 0.213f);
//        shitPath.lineTo((float) (size * 0.5f + size * 0.45f * Math.cos(-Math.PI / 2 - Math.PI / 10)),
//                (float) (size * 0.5f + size * 0.45f * Math.sin(-Math.PI / 2 - Math.PI / 10)));
//        shitPath.lineTo(size * 0.225f, size * 0.007f);
        shitPath.addArc(cornerL, getSemicircle(size * 0.726f, size * 0.111f, size * (0.675f), size * (0.197f), cornerL, 1), 180);
//        for (double i = Math.PI / 6 - Math.PI / 2; i < (2 * Math.PI - Math.PI / 6 - Math.PI / 2) - Math.PI / 18; i += Math.PI / 18) {
//            float x = (float) (size * 0.5f + size * 0.45f * Math.cos(i));
//            float y = (float) (size * 0.5f + size * 0.45f * Math.sin(i));
//            shitPath.addCircle(x, y, size * 0.05f, Path.Direction.CCW);
//            Log.d(TAG, "drawShit: " + x + " , " + y);
//        }

        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawPath(shitPath, paint);
    }

    public static float getSemicircle(float xStart, float yStart, float xEnd,
                                      float yEnd, RectF ovalRectOUT, int direction) {

        float centerX = xStart + ((xEnd - xStart) / 2);
        float centerY = yStart + ((yEnd - yStart) / 2);

        double xLen = (xEnd - xStart);
        double yLen = (yEnd - yStart);
        float radius = (float) (Math.sqrt(xLen * xLen + yLen * yLen) / 2);

        RectF oval = new RectF((float) (centerX - radius),
                (float) (centerY - radius), (float) (centerX + radius),
                (float) (centerY + radius));

        ovalRectOUT.set(oval);

        double radStartAngle = 0;
        if (direction == 0) {
            radStartAngle = Math.atan2(yStart - centerY, xStart - centerX);
        } else {
            radStartAngle = Math.atan2(yEnd - centerY, xEnd - centerX);
        }
        float startAngle = (float) Math.toDegrees(radStartAngle);

        return startAngle;

    }


    private void drawFaceBackground(Canvas canvas) {

        // 1
        paint.setColor(faceColor);
        paint.setStyle(Paint.Style.FILL);

        // 2
        float radius = size / 2f;

        // 3
        canvas.drawCircle(size / 2f, size / 2f, radius, paint);

        // 4
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);

        // 5
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint);

    }

    private void drawEyes(Canvas canvas) {

        // 1
        paint.setColor(eyesColor);
        paint.setStyle(Paint.Style.FILL);

        // 2
        RectF leftEyeRect = new RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f);

        canvas.drawOval(leftEyeRect, paint);

        // 3
        RectF rightEyeRect = new RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f);

        canvas.drawOval(rightEyeRect, paint);
    }

    private void drawMouth(Canvas canvas) {
        // 1
        mouthPath.moveTo(size * 0.22f, size * 0.7f);
        // 2
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f);
        // 3
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f);
        // 4
        paint.setColor(mouthColor);
        paint.setStyle(Paint.Style.FILL);
        // 5
        canvas.drawPath(mouthPath, paint);
    }

    public EmotionalFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSize(int size) {
        this.size = size;
    }
}
