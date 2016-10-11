package com.gcu.liam.muccoursework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Liam on 15/12/2015.
 */
public class venueOutput extends MainActivity implements View.OnClickListener
{
    //Declaration of Textviews and Imageviews to show all avaiable venues
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;

    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;

    Button btnBack1;
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.venue_screen);
        // Get the views and assign the images to the imageviews and add text to the textviews
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);

        String sImagePath1 = "drawable/burgerking";
        String sImagePath2 = "drawable/counthouse";
        String sImagePath3 = "drawable/ichiban";
        String sImagePath4 = "drawable/kfc";
        String sImagePath5 = "drawable/mcd";

        Context appContext = getApplicationContext();

        int imgResID1 = appContext.getResources().getIdentifier(sImagePath1, "drawable", "com.gcu.liam.muccoursework");
        int imgResID2 = appContext.getResources().getIdentifier(sImagePath2, "drawable","com.gcu.liam.muccoursework");
        int imgResID3 = appContext.getResources().getIdentifier(sImagePath3, "drawable","com.gcu.liam.muccoursework");
        int imgResID4 = appContext.getResources().getIdentifier(sImagePath4, "drawable","com.gcu.liam.muccoursework");
        int imgResID5 = appContext.getResources().getIdentifier(sImagePath5, "drawable","com.gcu.liam.muccoursework");

        iv1.setImageResource(imgResID1);
        iv2.setImageResource(imgResID2);
        iv3.setImageResource(imgResID3);
        iv4.setImageResource(imgResID4);
        iv5.setImageResource(imgResID5);

        tv1.setText("Burger King, Fast Food Restaurant");
        tv2.setText("Counting House, Pub Food ");
        tv3.setText("Ichiban, Japanese Noodle Bar");
        tv4.setText("KFC, Fried Chicken Fast Food");
        tv5.setText("McDonald's, American Fast Food");
//Get back button
        btnBack1 = (Button) findViewById(R.id.btnBack1);
        btnBack1.setOnClickListener(this);

    }

    public void onClick(View view)
    {
        //if back button is pressed then return to previous activity
        if (view == btnBack1) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}
