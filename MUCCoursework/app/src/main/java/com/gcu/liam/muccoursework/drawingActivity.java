package com.gcu.liam.muccoursework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Liam on 15/12/2015.
 */
public class drawingActivity extends AppCompatActivity
{
    // This class is responsible for creating a blue circle on the screen
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.drawing_screen);
        setContentView(new MyView(this));
    }

    public class MyView extends View
    {
        public MyView(Context context) { super(context);}

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            // Variables for drawing
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;

            //Calling new paint instance
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.BLUE);

            //Draw the circle
            canvas.drawCircle(x/2,y/2,radius,paint);
        }
    }
}
