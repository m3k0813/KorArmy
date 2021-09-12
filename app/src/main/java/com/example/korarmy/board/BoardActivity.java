package com.example.korarmy.board;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korarmy.MainActivity;
import com.example.korarmy.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity{
    
    private ImageView iv_create;
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Board> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerboard);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);        // 역순 출력
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("board");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Board board = snapshot.getValue(Board.class);
                    board.key = snapshot.getKey();
                    arrayList.add(board);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("BoardActivity", String.valueOf(error.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList, this, new CustomAdapter.ItemClickListener() {
            @Override
            public void onItemClicked(int position, Board board) {
                // 아이템 클릭 시 어뎁터와 소통 데이터베이스 키값을 넘겨줌
                String key = board.key;
                Intent intent = new Intent(getApplicationContext(), ViewBoardActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("ind", "1");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        // 글 작성하기
        iv_create = findViewById(R.id.iv_create);
        iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                intent.putExtra("ind", "1");
                startActivity(intent);
            }
        });
        
        // 첫 화면 가기
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
