package com.gcu.liam.muccoursework;

/**
 * Created by Liam on 15/12/2015.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.LinkedList;

public class laAsyncRSSParser extends AsyncTask <String, Integer, carParkData>
{
    private Context appContext;
    private String urlRSSToParse;
    public static LinkedList<carParkData> cpData;

    public laAsyncRSSParser (Context currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
        cpData=new LinkedList<>();
    }

    // A callback method executed on UI thread on starting the task

    @Override
    protected void onPreExecute()
    {
        //Message to indicate start of parsing

        Toast.makeText(appContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected carParkData doInBackground (String... params)
    {
        carParkData parsedData;
        laRSSParser rssParser = new laRSSParser();
        try {
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        parsedData = rssParser.getRSSDataItem();
        cpData.addAll(rssParser.parseList);

        return parsedData;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event handler

    @Override
    protected void onPostExecute(carParkData result)
    {
        //Message to indicate end of parsing
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }

}
