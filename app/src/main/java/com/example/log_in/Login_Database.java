package com.example.log_in;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Login_Database extends SQLiteOpenHelper {
    String TAG="AAA";
    public Login_Database(@Nullable Context context) {
        super(context, "LoginData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbs) {
        String query="create table Login_Data(ID integer primary key autoincrement,USERNAME text,PASSWORD text)";
        dbs.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void LoginUserData(String username, String password) {
        String query="insert into Login_Data(USERNAME,PASSWORD) values('"+username+"','"+password+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
}
