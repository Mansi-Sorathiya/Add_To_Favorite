package com.example.log_in;

import static com.example.log_in.MainActivity.editor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GridView_Activity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    GridView gridView;
    Button favourite, logout;

    Fav_Database db_fav;
    OutputStream outputStream;
    private File downloadFile;

    int img[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e,
            R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.gridview);
        favourite = findViewById(R.id.favourite);
        logout = findViewById(R.id.logout);

        db_fav=new Fav_Database(GridView_Activity.this);

        Image_Adapter adapter = new Image_Adapter(GridView_Activity.this,img,db_fav);
        gridView.setAdapter(adapter);

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridView_Activity.this, Favourite_Activity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridView_Activity.this, Login_Activity.class);
                editor.putBoolean("Login",false);
                editor.commit();
                startActivity(intent);
                finish();
            }
        });


    }
    void askpermission() {
        ActivityCompat.requestPermissions(GridView_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }

}