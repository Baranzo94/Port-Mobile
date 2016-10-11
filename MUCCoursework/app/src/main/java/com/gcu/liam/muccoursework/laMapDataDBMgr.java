package com.gcu.liam.muccoursework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Liam on 08/12/2015.
 */
public class laMapDataDBMgr extends SQLiteOpenHelper
{

    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.gcu.liam.muccoursework/databases/";
    private static final String DB_NAME = "lunchtimeInfo.s3db";
    private static final String TBL_LUNCHTIMEAPP = "lunchtimeApp";

    public static final String COL_ENTRYID = "entryID";
    public static final String COL_VENUE = "Venue";
    public static final String COL_LATITUDE = "Latitude";
    public static final String COL_LONGITUDE = "Longitude";

    private final Context appContext;

    public laMapDataDBMgr(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MAPLUNCH_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_LUNCHTIMEAPP + "("
                + COL_ENTRYID + " INTEGER PRIMARY KEY," + COL_VENUE
                + " TEXT," +  COL_LATITUDE + " FLOAT" + COL_LONGITUDE + " FLOAT" +")";
        db.execSQL(CREATE_MAPLUNCH_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_LUNCHTIMEAPP);
            onCreate(db);
        }
    }

    // ================================================================================
    // Creates a empty database on the system and rewrites it with your own database.
    // ================================================================================
    public void dbCreate() throws IOException {

        boolean dbExist = dbCheck();

        if(!dbExist){
            //By calling this method an empty database will be created into the default system path
            //of your application so we can overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    // ============================================================================================
    // Check if the database already exist to avoid re-copying the file each time you open the application.
    // @return true if it exists, false if it doesn't
    // ============================================================================================
    private boolean dbCheck(){

        SQLiteDatabase db = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);

        }catch(SQLiteException e){

            Log.e("SQLHelper", "Database not Found!");

        }

        if(db != null){

            db.close();

        }

        return db != null ? true : false;
    }

    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try {

            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            //transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }

            //Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }


    public void addaMapLunchEntry(laMapData aMapLunch) {

        ContentValues values = new ContentValues();
        values.put(COL_VENUE, aMapLunch.getVenue());
        values.put(COL_LATITUDE, aMapLunch.getLatitude());
        values.put(COL_LONGITUDE, aMapLunch.getLongitude());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_LUNCHTIMEAPP, null, values);
        db.close();
    }

    public laMapData getMapLunchEntry(String aMapLunchEntry) {
        String query = "Select * FROM " + TBL_LUNCHTIMEAPP + " WHERE " + COL_VENUE + " =  \"" + aMapLunchEntry + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        laMapData MapDataEntry = new laMapData();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            MapDataEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
            MapDataEntry.setVenue(cursor.getString(1));
            MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(2)));
            MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(3)));
            cursor.close();
        } else {
            MapDataEntry = null;
        }
        db.close();
        return MapDataEntry;
    }

    public boolean removeaMapLunchEntry(String aMapLunchEntry) {

        boolean result = false;

        String query = "Select * FROM " + TBL_LUNCHTIMEAPP + " WHERE " + COL_VENUE + " =  \"" + aMapLunchEntry + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return result;
    }

    public List<laMapData> allMapData()
    {
        String query = "Select * FROM " + TBL_LUNCHTIMEAPP;
        List<laMapData> laMapDataList = new ArrayList<laMapData>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                laMapData MapDataEntry = new laMapData();
                MapDataEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
                MapDataEntry.setVenue(cursor.getString(1));
                MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(2)));
                MapDataEntry.setLongitude(Float.parseFloat(cursor.getString(3)));
                laMapDataList.add(MapDataEntry);
                cursor.moveToNext();
            }
        } else {
            laMapDataList.add(null);
        }
        db.close();
        return laMapDataList;
    }

}
