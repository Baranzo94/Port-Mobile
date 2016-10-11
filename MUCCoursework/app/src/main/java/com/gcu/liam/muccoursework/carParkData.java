package com.gcu.liam.muccoursework;

/**
 * Created by Liam on 08/12/2015.
 */
import java.io.Serializable;

public class carParkData implements Serializable{


    //Variables defined for prep for parsing
    private String carParkIdentity;
    private String carParkOccupancy;


    public carParkData()
    {
        carParkIdentity = "";
        carParkOccupancy = "";
    }

    //Getters and Setters for Strings for the parser
    public String getcarParkIdentity()
    {
        return carParkIdentity;
    }

    public void setcarParkIdentity(String identity)
    {
        carParkIdentity = identity;
    }

    public String getcarParkOccupancy()
    {
        return carParkOccupancy;
    }

    public void setcarParkOccupancy(String occupancy)
    {
        carParkOccupancy = occupancy;
    }

    //Preparing variables to passed to parser, converted to string
    public String toString()
    {
        String carparkData;

        carparkData = "carParkData [itemTitle = " + carParkIdentity;
        carparkData = ", carParkOccupancy" + carParkOccupancy +"]";

        return carparkData;
    }
}
