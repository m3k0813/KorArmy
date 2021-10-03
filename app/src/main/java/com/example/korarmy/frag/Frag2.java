package com.example.korarmy.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.korarmy.BottomSheetDialog;
import com.example.korarmy.R;
import com.example.korarmy.database.Todo;
import com.example.korarmy.database.TodoAdapter;
import com.example.korarmy.database.TodoData;
import com.example.korarmy.database.TodoDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Frag2 extends Fragment implements BottomSheetDialog.BottomSheetListener {
    private View view;
    private CalendarView calendarView;
    private FloatingActionButton fab;
    private RecyclerView recyclerview_todo;
    TodoDatabase db;
    private LinearLayoutManager linearLayoutManager;
    private TodoAdapter todoAdapter;
    private ArrayList<TodoData> arrayList;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        init();


        calendarView = (CalendarView) view.findViewById(R.id.calendarview);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // todo
                Toast.makeText(getContext(), year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        fab = view.findViewById(R.id.floatingbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog();

            }
        });

        db = Room.databaseBuilder(container.getContext(), TodoDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();


        return view;
    }

    @Override
    public void onButtonClicked(String todo) {       // 일정 저장
        Toast.makeText(getContext(), todo, Toast.LENGTH_SHORT).show();
        db.todoDao().insert(new Todo(todo));


    }

    private void BottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.show(getChildFragmentManager(), "BottomSheet");
    }

    private void init(){
        recyclerview_todo = view.findViewById(R.id.recyclerview_todo);
        linearLayoutManager = new LinearLayoutManager( getActivity().getApplicationContext());
        recyclerview_todo.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        todoAdapter = new TodoAdapter(arrayList);
        recyclerview_todo.setAdapter(todoAdapter);

        TodoData todoData = new TodoData("테스트");
        arrayList.add(todoData);
        todoAdapter.notifyDataSetChanged();
    }
}
