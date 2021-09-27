package com.example.korarmy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private View view;
    private BottomSheetListener listener;
    private Button savebtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_bottom_sheet, container, false);

        savebtn = view.findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked("바텀 숨겨짐");
                dismiss();

            }
        });
        return view;

    }
    public interface BottomSheetListener{
        void onButtonClicked(String text);
    }

    public BottomSheetDialog(BottomSheetListener listener) {
        this.listener = listener;
    }
}
