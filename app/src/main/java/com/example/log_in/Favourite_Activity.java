package com.example.log_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import java.util.List;

public class Favourite_Activity extends AppCompatActivity {

    GridView gridView;
    Fav_Database fdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        fdb = new Fav_Database(Favourite_Activity.this);

        gridView = findViewById(R.id.gridView);

        List<byte[]> imageList = getFavoriteImagesFromDatabase();
        Fav_Adapter adapter = new Fav_Adapter(imageList);
        gridView.setAdapter(adapter);

    }
    public List<byte[]> getFavoriteImagesFromDatabase() {
        return (List<byte[]>) fdb.getFavoriteImages();
    }


}