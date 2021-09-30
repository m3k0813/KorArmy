package com.example.korarmy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private View view;
    private BottomSheetListener listener;
    private Button savebtn;
    private EditText edit_todo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_bottom_sheet, container, false);

        edit_todo = view.findViewById(R.id.edit_todo);

        savebtn = view.findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = edit_todo.getText().toString();
                listener.onButtonClicked(todo);
                dismiss();

            }
        });
        return view;

    }
    public interface BottomSheetListener{
        void onButtonClicked(String todo);
    }

    public BottomSheetDialog(BottomSheetListener listener) {
        this.listener = listener;
    }
}
