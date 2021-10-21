package com.example.korarmy.frag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.korarmy.BottomSheetDialog;
import com.example.korarmy.ItemTouchHelperCallback;
import com.example.korarmy.R;
import com.example.korarmy.database.Todo;
import com.example.korarmy.database.TodoAdapter;
import com.example.korarmy.database.TodoDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Frag2 extends Fragment{
    private View view;
    private CalendarView calendarView;
    private FloatingActionButton fab;
    private ImageButton del_Btn, cha_Btn, save_Btn;
    private TextView textView2;
    private EditText contextEditText;
    String readDay, str;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        save_Btn = view.findViewById(R.id.save_Btn);
        del_Btn = view.findViewById(R.id.del_Btn);
        cha_Btn = view.findViewById(R.id.cha_Btn);
        textView2 = view.findViewById(R.id.textView2);
        contextEditText = view.findViewById(R.id.contextEditText);

        // 캘린더뷰
        calendarView = (CalendarView) view.findViewById(R.id.calendarview);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // todo
                Toast.makeText(getContext(), year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setText("");
                checkDay(year, month, dayOfMonth);
            }
        });

        save_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveDiary(readDay);
                str = contextEditText.getText().toString();
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);

            }
        });

        // 플로팅버튼, 일정 저장
//        fab = view.findViewById(R.id.floatingbtn);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BottomSheetDialog();
//
//            }
//        });

        return view;
    }

//    @Override
//    public void onButtonClicked(String todo) {       // 일정 저장
//        Toast.makeText(getContext(), todo, Toast.LENGTH_SHORT).show();
//
//    }
//
//    private void BottomSheetDialog() {
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.show(getChildFragmentManager(), "BottomSheet");
//    }

    public void checkDay(int cYear, int cMonth, int cDay)
    {
        readDay = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt";
        FileInputStream fis;

        try
        {
            fis = getContext().openFileInput(readDay);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str = new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    File file = new File(getContext().getFilesDir(), readDay) ;
                    if (file.exists()) {
                        file.delete();
                    }
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                }
            });
            if (textView2.getText() == null)
            {
                textView2.setVisibility(View.INVISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay)
    {
        FileOutputStream fos;
        try
        {
            fos = getContext().openFileOutput(readDay, getContext().MODE_NO_LOCALIZED_COLLATORS);
            String content = contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 리사이클러뷰(일정)
//    private void init(){
//        recyclerview_todo = view.findViewById(R.id.recyclerview_todo);
//        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerview_todo.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview_todo.getContext(),new LinearLayoutManager(getActivity()).getOrientation());
//        recyclerview_todo.addItemDecoration(dividerItemDecoration);
//
//        arrayList = new ArrayList<>();
//        todoAdapter = new TodoAdapter();
//        recyclerview_todo.setAdapter(todoAdapter);
//
//        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(todoAdapter));
//        itemTouchHelper.attachToRecyclerView(recyclerview_todo);       // 스와이프 기능
//
//
//        int size = db.todoDao().getAll().size();
//        for(int i = 0; i < size; i++){
//            todoAdapter.addItems(db.todoDao().getAll().get(i));
//        }
//    }
}
