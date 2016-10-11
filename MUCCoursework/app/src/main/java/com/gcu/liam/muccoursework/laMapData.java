package com.gcu.liam.muccoursework;

import java.io.Serializable;

/**
 * Created by Liam on 08/12/2015.
 */
public class laMapData implements Serializable
{
    // Set variables for retrieval of specified data from database
    private int entryID;
    private String Venue;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

    public int getEntryID()
    {
        return entryID;
    }

    public void setEntryID(int entryID)
    {
        this.entryID = entryID;
    }

    public String getVenue()
    {
        return Venue;
    }

    public void setVenue(String venue)
    {
        this.Venue = venue;
    }

    public float getLatitude()
    {
    return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString()
    {
        String mapData;
        mapData = "laLunchtimeApp [entryID=" + entryID;
        mapData = ", Venue=" + Venue;
        mapData = ", Latitude=" + Latitude;
        mapData = ", Longitude" + Longitude +"]";
        return mapData;
    }
}
