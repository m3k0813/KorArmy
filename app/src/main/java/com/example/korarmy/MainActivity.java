package com.example.korarmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText TextInput_email, TextInput_pwd;
    TextView findpwdbtn, signupbtn;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextInput_email = findViewById(R.id.TextInput_email);
        TextInput_pwd = findViewById(R.id.TextInput_pwd);
        findpwdbtn = findViewById(R.id.findpwdbtn);
        signupbtn = findViewById(R.id.signupbtn);

        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = TextInput_email.getText().toString();
                String pwd = TextInput_pwd.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("pwd", pwd);
                startActivity(intent);
            }
        });
        signupbtn.setClickable(true);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}