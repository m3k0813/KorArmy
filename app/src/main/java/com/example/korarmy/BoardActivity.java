package com.example.korarmy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity{
    
    private ImageView iv_create;
    private ImageView iv_back;
    private RecyclerView recyclerView;
    private ArrayList<BoardData> arrayList;
    private BoardAdapter boardAdapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerboard);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        boardAdapter = new BoardAdapter(arrayList);
        recyclerView.setAdapter(boardAdapter);
        BoardData boardData = new BoardData("Test", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세", "3:02");
        BoardData boardData2 = new BoardData("애국가", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세", "3:04");
        BoardData boardData3 = new BoardData("테스트", "테스트", "3:05");
        arrayList.add(boardData);
        arrayList.add(boardData2);
        arrayList.add(boardData3);
        boardAdapter.notifyDataSetChanged();

        // 글 작성하기
        iv_create = findViewById(R.id.iv_create);
        iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });
        
        // 첫 화면 가기
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
