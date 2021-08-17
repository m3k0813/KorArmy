package com.example.korarmy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.microedition.khronos.egl.EGLDisplay;

public class WriteActivity extends AppCompatActivity {

    private ImageView iv_close;
    private ImageView iv_check;
    private EditText edit_title;
    private EditText edit_ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_ctx = (EditText) findViewById(R.id.edit_ctx);

        // 취소
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 작성
        iv_check = findViewById(R.id.iv_check);
        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });
    }
        public void write() {    
            String title = edit_title.getText().toString();
            String ctx = edit_ctx.getText().toString();

            // if null==title && null == ctx
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            BoardData boardData = new BoardData(title, ctx);
            database.child("message").push().setValue(boardData);
            finish();
    }
}

