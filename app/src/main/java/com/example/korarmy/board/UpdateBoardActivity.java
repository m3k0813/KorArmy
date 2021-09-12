package com.example.korarmy.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.MainActivity;
import com.example.korarmy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UpdateBoardActivity extends AppCompatActivity {

    private ImageView iv_close;
    private ImageView iv_check;
    private EditText ub_title;
    private EditText ub_ctx;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String key;
    String ind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateboard);

        ub_title = findViewById(R.id.ub_title);
        ub_ctx = findViewById(R.id.ub_ctx);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        ind = intent.getStringExtra("ind");

        if (ind.equals("1")) {
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("board").child(key);
        } else if (ind.equals("2")) {
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("secretboard").child(key);
        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board = snapshot.getValue(Board.class);
                ub_title.setText(board.getTitle());
                ub_ctx.setText(board.getCtx());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // 취소
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 수정
        iv_check = findViewById(R.id.iv_check);
        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = ub_title.getText().toString();
                String ctx = ub_ctx.getText().toString();
                if (title.isEmpty() || ctx.isEmpty()) {
                    Toast.makeText(UpdateBoardActivity.this, "텍스트를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    update(title, ctx);
                }
            }
        });
    }
    // 글 수정
    public void update(String title, String ctx) {
        long now = System.currentTimeMillis();    // 현재 시간 저장
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("title",title);
        hashMap.put("ctx",ctx);
        hashMap.put("time", String.valueOf(now));


        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        if (ind.equals("1")) {
            database.child("board").child(key).updateChildren(hashMap);
        } else if (ind.equals("2")) {
            database.child("secretboard").child(key).updateChildren(hashMap);
        }
        Toast.makeText(getApplicationContext(), "게시글이 수정되었습니다.", Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(UpdateBoardActivity.this, ViewBoardActivity.class); //화면 전환
                intent.putExtra("key", key);
                intent.putExtra("ind", ind);
                startActivity(intent);
                finish();
            }
        }, 1000); //딜레이 타임 조절
    }
}
