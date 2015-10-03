package com.keenanzucker.photostream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/**
 * Created by keenan on 10/2/15.
 * Database helper used to save images, retrieve the saved images, and delete images from the SQL database
 * Database is simple table that holds saved image URLs.
 */
public class DbHelper{

    private DatabaseService dbs;

    public DbHelper(Context context){

        dbs = new DatabaseService(context);
    }

    public void saveImage(String url)
    {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbs.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ImageContract.FeedEntry.IMAGE_ITEM_COLUMN, url);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                ImageContract.FeedEntry.TABLE_NAME,
                ImageContract.FeedEntry.IMAGE_ITEM_COLUMN,
                values);
    }

    public ArrayList<String> getSavedImages(){
        ArrayList<String> urls = new ArrayList<>();

        SQLiteDatabase db = dbs.getReadableDatabase();
        String[] projection = {
                ImageContract.FeedEntry.IMAGE_ITEM_COLUMN
        };

        Cursor c = db.query(
                ImageContract.FeedEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        c.moveToFirst();
        while (c.moveToNext()) {
            urls.add(c.getString(c.getColumnIndexOrThrow(ImageContract.FeedEntry.IMAGE_ITEM_COLUMN)));
        }
        return urls;
    }

    public boolean deleteImage(String url) {
        String whereClause = ImageContract.FeedEntry.IMAGE_ITEM_COLUMN + "=?";
        String[] whereArgs = new String[] { url };
        SQLiteDatabase db = dbs.getWritableDatabase();
        return db.delete(ImageContract.FeedEntry.TABLE_NAME, whereClause, whereArgs) > 0;
    }
}
