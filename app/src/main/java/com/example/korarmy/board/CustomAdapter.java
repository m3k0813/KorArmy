package com.example.korarmy.board;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korarmy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// 자유게시판 어답터
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Board> arrayList;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public CustomAdapter(ArrayList<Board> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_recycler, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_ctx.setText(arrayList.get(position).getCtx());
        holder.tv_time.setText(arrayList.get(position).getTime());
//        holder.tv_uid.setText(arrayList.get(position).getUid());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference().child("board");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapShot : snapshot.getChildren()) {
                            String pushKey = dataSnapShot.getKey();
                            Intent intent = new Intent(v.getContext(), ViewBoardActivity.class);
                            intent.putExtra("id", pushKey);
                            v.getContext().startActivity(intent);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_ctx;
        TextView tv_time;
        TextView tv_uid;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.tv_ctx = itemView.findViewById(R.id.tv_ctx);
            this.tv_time = itemView.findViewById(R.id.tv_time);
//            this.tv_uid = itemView.findViewById(R.id.tv_uid);
        }
    }
}