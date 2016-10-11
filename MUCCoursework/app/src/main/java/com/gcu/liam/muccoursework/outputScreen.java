package com.gcu.liam.muccoursework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Liam on 29/11/2015.
 */
public class outputScreen extends MainActivity implements View.OnClickListener
{
    //Declare buttons and textviews
    Button btnVenues;
    Button btnMap;
    //Button btnChoice;
    TextView tvChoice;

    //Generate a random number to be used to randomly select a index in the array
    Random rand = new Random();
    int n = rand.nextInt(5);

    //Get the list of data retreived from the database
    List<laMapData> mapDataList;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_screen);

        tvChoice = (TextView) findViewById(R.id.tvChoice);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);

        btnVenues = (Button) findViewById(R.id.btnVenues);
        btnVenues.setOnClickListener(this);

        /*btnChoice = (Button) findViewById(R.id.btnChoice);
        btnChoice.setOnClickListener(this);*/

        //
        mapDataList = new ArrayList<laMapData>();
        laMapDataDBMgr mapDB = new laMapDataDBMgr(this,"lunchtimeInfo.s3db",null,1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapDataList = mapDB.allMapData();
        getChoice();

    }

    protected void getChoice()
    {
        // Gets the data and randomly selects a choice from the retrieved array list
        laMapData mapData;
        String userChoice;

        for(int i = 0; i < mapDataList.size(); i++)
        {
            //i = i + n;
            mapData = mapDataList.get(0 + n);
            userChoice = mapData.getVenue();
            tvChoice.setText(userChoice);

        }
    }

    public void onClick(View view)
    {

        Intent venueOutput = new Intent(getApplicationContext(), venueOutput.class);
        Intent laMap = new Intent(this, laMapActivity.class);

        if (view == btnVenues)
        {
            // hitting this button starts the venue activity
            startActivity(venueOutput);
        }

        if (view == btnMap)
        {
            // this starts the map activity
            this.startActivity(laMap);
        }
       /* if(view == btnChoice)
        {
            rand = new Random();
            getChoice();
        }*/



    }

}
