package com.example.korarmy.frag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.korarmy.R;
import com.example.korarmy.RecyAdapter;
import com.example.korarmy.RecyclerData;
import com.example.korarmy.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Frag1 extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyAdapter recyAdapter;
    private ArrayList<RecyclerData> arrayList= new ArrayList<>();
    private TextView army; // 군인 정보
    private TextView classes; // 계급
    private ImageView iv_classes; // 계급이미지
    private TextView end_day; // 전역 날짜
    private TextView nextclasses; // 다음 계급
    private TextView nextClasses_day; // 다음 계급까지 남은 날짜
    private TextView enlistment_date; // 입대 날짜
    private TextView discharge_date; // 전역 날짜
    private TextView salary; // 월급
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String enlistment_d;
    


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        army = view.findViewById(R.id.army);
        classes = view.findViewById(R.id.classes);
        iv_classes = view.findViewById(R.id.iv_classes);
        end_day = view.findViewById(R.id.end_day);
        nextclasses = view.findViewById(R.id.nextClasses);
        nextClasses_day = view.findViewById(R.id.nextClasses_day);
        enlistment_date = view.findViewById(R.id.enlistment_date);
        discharge_date = view.findViewById(R.id.discharge_date);
        salary = view.findViewById(R.id.salary);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("users").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                army.setText(users.getArmy());    // 군대 정보
                enlistment_date.setText(users.getEnlistment_date());  // 입대일
                cal_discharge_date(users.getArmy(), users.getEnlistment_date());    // 전역일 계산
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyAdapter = new RecyAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(recyAdapter);
        RecyclerData recyclerData = new RecyclerData("자유게시판");
        RecyclerData recyclerData2 = new RecyclerData("비밀게시판");
        if (arrayList.isEmpty()) {
            arrayList.add(recyclerData);
            arrayList.add(recyclerData2);
            recyAdapter.notifyDataSetChanged();
        }

        return view;
    }

    // 전역일 계산
    private void cal_discharge_date(String army, String enl){
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = dateFormat.parse(enl);
            cal.setTime(date);

            if (army.equals("육군")) {
                cal.add(Calendar.MONTH, 18);
                cal.add(Calendar.DATE, -1);
            }else if(army.equals("해군")){
                cal.add(Calendar.MONTH, 20);
                cal.add(Calendar.DATE, -1);
            }else if(army.equals("공군")){
                cal.add(Calendar.MONTH, 21);
                cal.add(Calendar.DATE, -1);
            } else if (army.equals("보충역")) {
                cal.add(Calendar.MONTH, 21);
                cal.add(Calendar.DATE, -1);
            } else if (army.equals("해병대")) {
                cal.add(Calendar.MONTH, 18);
                cal.add(Calendar.DATE, -1);
            }

            String to = dateFormat.format(cal.getTime());
            discharge_date.setText(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
