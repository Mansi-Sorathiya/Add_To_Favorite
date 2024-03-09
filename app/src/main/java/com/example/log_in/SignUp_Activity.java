package com.example.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp_Activity extends AppCompatActivity {

    EditText name, email, password;
    Button signup;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        db = new MyDataBase(SignUp_Activity.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (!n.isEmpty() && !em.isEmpty() && !pass.isEmpty()) {

                    db.SignupData(name.getText().toString(), email.getText().toString(), password.getText().toString());
                    Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUp_Activity.this, "Error..!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}
