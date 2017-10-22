package com.example.kibb.xpera.damiz.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by KIBB on 8/30/2017.
 */
import com.example.kibb.xpera.R;
public class WebViewProgressBar extends View {
    private int progress = 1;
    private final static int HEIGHT = 5;
    private Paint paint;

    public WebViewProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebViewProgressBar(Context context) {
        super(context);
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(HEIGHT);
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, HEIGHT, paint);
    }
}
