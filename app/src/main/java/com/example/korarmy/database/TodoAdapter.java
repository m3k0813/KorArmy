package com.example.korarmy.database;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korarmy.R;
import com.firebase.ui.auth.data.model.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {


    private ArrayList<Todo> arrayList = new ArrayList<>();


    @NonNull
    @Override
    public TodoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_recycler, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.MyViewHolder holder, int position) {
//        holder.tv_todo.setText(arrayList.get(position).getTodo());
        holder.onBind(arrayList.get(position), position);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = holder.tv_todo.getText().toString();
                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItems(Todo todo) {
        arrayList.add(todo);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_todo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_todo = (TextView) itemView.findViewById(R.id.tv_todo);
        }

        public void onBind(Todo todo, int position) {
            tv_todo.setText(todo.getTodo());
        }
    }
}
