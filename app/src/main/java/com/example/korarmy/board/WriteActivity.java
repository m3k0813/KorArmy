package com.example.korarmy.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

                String title = edit_title.getText().toString();
                String ctx = edit_ctx.getText().toString();
                if (title.isEmpty() || ctx.isEmpty()) {
                    Toast.makeText(WriteActivity.this, "텍스트를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    write(title, ctx);
                }
            }
        });
    }
        public void write(String title, String ctx) {
            // 로그인 uid
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            String uid = user.getDisplayName();


            long now = System.currentTimeMillis();    // 현재 시간 저장
            HashMap<Object, String> hashMap = new HashMap<>();
            hashMap.put("title",title);
            hashMap.put("ctx",ctx);
            hashMap.put("time", String.valueOf(now));

            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            database.child("board").push().setValue(hashMap);
            Toast.makeText(getApplicationContext(), "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
            startActivity(intent);
    }
}

