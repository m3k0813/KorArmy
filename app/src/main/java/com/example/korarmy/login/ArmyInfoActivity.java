package com.example.korarmy.login;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.example.korarmy.board.BoardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ArmyInfoActivity extends AppCompatActivity {

    private String enl;
    private String dis;
    private String username;
    private String email;
    private String pwd;
    private String army;
    private Button button;
    String[] armys = {"육군", "해군", "공군", "해병대", "보충역"};
    private DatePickerDialog datePickerDialog;
    private Button select_enl;
    private TextView select_dis;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armyinfo);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        username = intent.getStringExtra("username");
        pwd = intent.getStringExtra("pwd");

        Spinner spinner = (Spinner) findViewById(R.id.armyspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, armys
        );

        // 드롭다운 클릭 시 선택 창
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 스피너에 어댑터 설정
        spinner.setAdapter(adapter);

        // 스피너에서 선택 했을 경우 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                army = armys[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        initDatePicker();
        select_enl = findViewById(R.id.select_enl);
        select_enl.setText(getTodayDate());
        select_dis = findViewById(R.id.select_dis);

        button = findViewById(R.id.confirmbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enl==null) {
                    Toast.makeText(ArmyInfoActivity.this, "입대일을 설정해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("username", username);
                    hashMap.put("army", army);
                    hashMap.put("enlistment_date", enl);
                    hashMap.put("discharge_date", dis);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();

                    // 파이어베이스 데이터베이스 저장
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    database.child("users").child(uid).setValue(hashMap);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day =  calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                select_enl.setText(date);
                enl = date;    // 입대일 설정

                // 제대일 설정
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                try {
                    Date enlDate = dateFormat.parse(enl);
                    cal.setTime(enlDate);
                    cal.add(Calendar.MONTH, 18);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    String to = dateFormat.format(cal.getTime());
                    dis = to;
                    select_dis.setText(to);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        Calendar calendar = Calendar.getInstance();
        int day =  calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return year + "-" +getMonthFormat(month) + "-" + day;
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return "1";
        }
        if (month == 2) {
            return "2";
        }
        if (month == 3) {
            return "3";
        }
        if (month == 4) {
            return "4";
        }
        if (month == 4) {
            return "4";
        }
        if (month == 5) {
            return "5";
        }
        if (month == 6) {
            return "6";
        }
        if (month == 7) {
            return "7";
        }
        if (month == 8) {
            return "8";
        }
        if (month == 9) {
            return "9";
        }
        if (month == 10) {
            return "10";
        }
        if (month == 11) {
            return "11";
        }
        if (month == 12) {
            return "12";
        }

        return "1";  // default
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}

