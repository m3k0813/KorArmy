package com.example.korarmy.board;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.MainActivity;
import com.example.korarmy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewBoardActivity extends AppCompatActivity {

    private TextView vb_title;
    private TextView vb_ctx;
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

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("board").child(key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board = snapshot.getValue(Board.class);
                vb_title.setText(board.getTitle());
                vb_ctx.setText(board.getCtx());
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
}
