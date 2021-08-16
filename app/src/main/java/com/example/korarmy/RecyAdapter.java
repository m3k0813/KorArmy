package com.example.korarmy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

// 메인액티비티 리사이클러뷰 아답터
public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.CustomViewHolder> {

    private ArrayList<RecyclerData> arrayList;

    public RecyAdapter(FragmentActivity activity, ArrayList<RecyclerData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_board.setText(arrayList.get(position).getTv_title());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                String curName = holder.tv_board.getText().toString();
                if (position == 0) {
                    Intent intent = new Intent(v.getContext(), BoardActivity.class);
                    v.getContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent1 = new Intent(v.getContext(), SecretBoardActivity.class);
                    v.getContext().startActivity(intent1);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_board;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_board = (TextView) itemView.findViewById(R.id.tv_board);
        }
    }


}
