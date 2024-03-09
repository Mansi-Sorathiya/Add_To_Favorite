package com.example.log_in;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Fav_Database extends SQLiteOpenHelper {
    String TAG="AAA";
    public Fav_Database(@Nullable Context context) {
        super(context, "Favourite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbs) {
        String query = "CREATE TABLE FavImages (ID INTEGER PRIMARY KEY AUTOINCREMENT, IMAGE BLOB)";
        dbs.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addImages(byte[] image) {
        ContentValues values = new ContentValues();
        values.put("IMAGE", image);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("FavImages", null, values);

    }


    public List<byte[]> getFavoriteImages() {
        List<byte[]> imageList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT IMAGE FROM FavImages";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex("IMAGE"));
                imageList.add(imageByteArray);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return imageList;
    }
}
