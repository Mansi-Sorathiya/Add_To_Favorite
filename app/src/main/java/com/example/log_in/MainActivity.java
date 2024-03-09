package com.example.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    Boolean Login=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textview);
        preferences = getSharedPreferences("myPref",MODE_PRIVATE);
        editor = preferences.edit();
        Login=preferences.getBoolean("Login",false);

        CountDownTimer timer=new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText("Hello...!  "  + (l / 1000));
            }

            @Override
            public void onFinish() {
//                Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
//                startActivity(intent);

                if(Login){
                    Intent intent=new Intent(MainActivity.this, GridView_Activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(MainActivity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();
    }
}