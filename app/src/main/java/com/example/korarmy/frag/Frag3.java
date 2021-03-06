package com.example.korarmy.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.korarmy.R;
import com.example.korarmy.Users;
import com.example.korarmy.board.BoardActivity;
import com.example.korarmy.board.QuestionBoardActivity;
import com.example.korarmy.board.SecretBoardActivity;
import com.example.korarmy.login.LoginActivity;
import com.example.korarmy.login.MySharedPreferences;
import com.example.korarmy.login.MyidActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Frag3 extends Fragment {
    private View view;
    private TextView logoutbtn;
    private TextView my_id;
    private TextView signoutbtn;
    private TextView id_info;
    private FirebaseAuth mAuth ;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);

        //  로그인 uid
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("users").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                String username = users.getUsername();
                my_id.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        my_id = view.findViewById(R.id.my_id);
        my_id.setText(uid);

        // 회원정보변경
        id_info = view.findViewById(R.id.id_info);
        id_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyidActivity.class);
                startActivity(intent);
            }
        });

        // 회원탈퇴
        signoutbtn = view.findViewById(R.id.signoutbtn);
        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 파이어베이스 계정 삭제
                mAuth = FirebaseAuth.getInstance();
                mAuth.getCurrentUser().delete();
                
                // 유저DB정보 삭제
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference().child("users").child(uid);
                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
 
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("error: "+e.getMessage());
                    }
                });
                logout();
            }
        });

        // 로그아웃
        logoutbtn = view.findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return view;
    }
    private void logout() {
        MySharedPreferences.userEmailClear(getActivity().getApplicationContext());
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}