package com.keenanzucker.photostream;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by keenan on 10/2/15.
 */


public class DatabaseService extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DatabaseService(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        final String TEXT_TYPE = " TEXT";
        final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + ImageContract.FeedEntry.TABLE_NAME + " (" +
                        ImageContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        ImageContract.FeedEntry.IMAGE_ITEM_COLUMN + TEXT_TYPE + " )";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ImageContract.FeedEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}