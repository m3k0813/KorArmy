package com.example.korarmy.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ViewBoardActivity extends AppCompatActivity {

    private TextView vb_title;
    private TextView vb_ctx;
    private TextView vb_time;
    private ImageView iv_back;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewboard);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        vb_title = findViewById(R.id.vb_title);
        vb_ctx = findViewById(R.id.vb_ctx);
        vb_time = findViewById(R.id.vb_time);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("board").child(key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board = snapshot.getValue(Board.class);
                vb_title.setText(board.getTitle());
                vb_ctx.setText(board.getCtx());
                vb_time.setText(setDate(board.getTime()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // 뒤로 가기
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(intent);
            }
        });

    }
    // 날짜 출력
    public String setDate(String getTime){
        long time = Long.parseLong(getTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm=dd hh:mm:sss"); // 날짜와 시간
        Date date = new Date(time);
        String dates = dateFormat.format(date);
        return dates;
    }
}
