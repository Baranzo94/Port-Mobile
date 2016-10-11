package com.gcu.liam.muccoursework;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Liam on 15/12/2015.
 */
public class laRSSParser {

    private carParkData RSSDataItem;
    public static LinkedList<carParkData> parseList = new LinkedList<>();

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setcarParkIdentity(sItemData);
        RSSDataItem.setcarParkOccupancy(sItemData);

    }

    public carParkData getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public laRSSParser()
    {
        this.RSSDataItem =  new carParkData();
        setRSSDataItem(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        try
        {
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    if (theParser.getName().equalsIgnoreCase("situation"))
                    {
                        this.RSSDataItem = new carParkData();
                    }
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("carParkIdentity"))
                    {
                        // Now just get the associated text
                        String temp = theParser.nextText();
                        // store data in class
                        RSSDataItem.setcarParkIdentity(temp);
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("occupiedSpaces"))
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                            RSSDataItem.setcarParkOccupancy(temp);
                            parseList.add(this.RSSDataItem);
                        }
            }

                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag","Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
