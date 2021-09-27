package com.example.korarmy.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.korarmy.BottomSheetDialog;
import com.example.korarmy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class Frag2 extends Fragment implements BottomSheetDialog.BottomSheetListener {
    private View view;
    private CalendarView calendarView;
    private TextView firsttodo;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendarview);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // todo
                Toast.makeText(getContext(), year+"/"+(month+1)+"/"+dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        fab = view.findViewById(R.id.floatingbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog();

            }
        });

        return view;
    }

    @Override
    public void onButtonClicked(String text) {       // 일정 저장
        Toast.makeText(getContext(), "숨기기", Toast.LENGTH_SHORT).show();
    }

    private void BottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.show(getChildFragmentManager(), "BottomSheet");
    }
}
