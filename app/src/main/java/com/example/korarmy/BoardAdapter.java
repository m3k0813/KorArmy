//package com.example.korarmy;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CustomViewHolder> {
//    private ArrayList<BoardData> arrayList;
//
//    public BoardAdapter(ArrayList<BoardData> arrayList) {
//        this.arrayList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_recycler, parent, false);
//        CustomViewHolder holder = new CustomViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        holder.tv_title.setText(arrayList.get(position).getTv_title());
//        holder.tv_ctx.setText(arrayList.get(position).getTv_ctx());
//        holder.tv_time.setText(arrayList.get(position).getTv_time());
//        holder.tv_uid.setText(arrayList.get(position).getUid());
//
//        holder.itemView.setTag(position);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != arrayList ? arrayList.size() : 0);
//    }
//
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//
//        protected TextView tv_title;
//        protected TextView tv_ctx;
//        protected TextView tv_time;
//        protected TextView tv_uid;
//
//        public CustomViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
//            this.tv_ctx = (TextView) itemView.findViewById(R.id.tv_ctx);
//            this.tv_time = (TextView) itemView.findViewById(R.id.tv_time);
//            this.tv_uid = (TextView) itemView.findViewById(R.id.tv_uid);
//        }
//    }
//}
