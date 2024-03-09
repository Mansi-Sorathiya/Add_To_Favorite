package com.example.log_in;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper {
    String TAG="HHH";
    public MyDataBase(@Nullable Context context) {
        super(context, "LogIn", null, 1);
        Log.d(TAG, "MyDataBase:  Database Created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table LogInData(ID integer primary key autoincrement,NAME text,EMAIL text,PASSWORD text)";
        db.execSQL(query);
        Log.d(TAG, "onCreate: Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void SignupData(String name, String email, String password) {
        String query="insert into LogInData(NAME,EMAIL,PASSWORD) values('"+name+"','"+email+"','"+password+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);

    }


}
