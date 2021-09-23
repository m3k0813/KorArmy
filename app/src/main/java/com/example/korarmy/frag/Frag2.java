package com.example.korarmy.frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.korarmy.BottomSheetDialog;
import com.example.korarmy.R;


public class Frag2 extends Fragment implements BottomSheetDialog.BottomSheetListener {
    private View view;
    private CalendarView calendarView;

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
                BottomSheetDialog();
            }
        });

        return view;
    }

    @Override
    public void onButtonClicked(String text) {
        Toast.makeText(getContext(), "숨기기", Toast.LENGTH_SHORT).show();
    }

    private void BottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.show(getChildFragmentManager(), "BottomSheet");
    }
}
