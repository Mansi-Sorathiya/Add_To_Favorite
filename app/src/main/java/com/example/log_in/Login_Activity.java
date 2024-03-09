package com.example.log_in;

import static com.example.log_in.MainActivity.editor;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    EditText username, password;
    Button login;
    Login_Database login_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login_db = new Login_Database(Login_Activity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (!user.isEmpty() && !pass.isEmpty())
                {
                    login_db.LoginUserData(username.getText().toString(),password.getText().toString());
//                    Intent intent = new Intent(Login_Activity.this, GridView_Activity.class);
//                    startActivity(intent);

                    editor.putBoolean("Login",true);
                    editor.putString("name", String.valueOf(username));
//                    editor.putString("email", );
                    editor.putString("password", String.valueOf(password));
                    editor.commit();
                    Intent intent = new Intent(Login_Activity.this, GridView_Activity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Login_Activity.this, "Error...!!1", Toast.LENGTH_SHORT).show();
                }
            }
            });


        }
    }