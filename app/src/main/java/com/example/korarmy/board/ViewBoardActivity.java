package com.example.korarmy.board;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private TextView vb_update;
    private TextView vb_delete;
    String uid;    // 데이터데이스에 저장된 uid
    String key;    // 데이터베이스 데이터 키값
    private ImageView iv_back;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewboard);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        vb_title = findViewById(R.id.vb_title);
        vb_ctx = findViewById(R.id.vb_ctx);
        vb_time = findViewById(R.id.vb_time);
        vb_update = findViewById(R.id.vb_update);
        vb_delete = findViewById(R.id.vb_delete);

        // 데이터베이스 불러오기
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("board").child(key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Board board = snapshot.getValue(Board.class);
                vb_title.setText(board.getTitle());
                vb_ctx.setText(board.getCtx());
                vb_time.setText(setDate(board.getTime()));
                uid = board.getUid();
                Writer();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // 삭제버튼
        vb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBoard();
            }
        });
        // 수정버튼
        vb_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBoard();
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm=dd hh:mm:ss"); // 날짜와 시간
        Date date = new Date(time);
        String dates = dateFormat.format(date);
        return dates;
    }

    // 작성자가 작성한 글을 눌렀을 때
    public void Writer(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUid = user.getUid(); // 로그인한 uid

        if (!uid.equals(currentUid)) {
            vb_update.setVisibility(View.GONE);
            vb_delete.setVisibility(View.GONE);
        }
    }

    public void deleteBoard() {
        database.getReference().child("Board").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "삭제 성공", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("error: "+e.getMessage());
                Toast.makeText(getApplicationContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateBoard() {
        Intent intent = new Intent(getApplicationContext(), UpdateBoardActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
}
