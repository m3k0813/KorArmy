package com.example.korarmy.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.example.korarmy.Users;
import com.example.korarmy.board.Board;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

// 회원정보변경 액티비티
public class MyidActivity extends AppCompatActivity {
    private TextView username_change;
    private TextView pwd_change;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private EditText current_pwd, new_pwd, renew_pwd;
    LinearLayout dialogView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myid);

        username_change = findViewById(R.id.username_change);
        pwd_change = findViewById(R.id.pwd_change);

        // 이름 변경
        username_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MyidActivity.this);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MyidActivity.this);
                dlg.setTitle("이름 변경");
                dlg.setMessage("변경할 이름을 입력하세요.");
                dlg.setView(editText);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("username", editText.getText().toString());

                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        database.child("users").child(uid).updateChildren(hashMap);
                        Toast.makeText(MyidActivity.this, "이름이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dlg.show();
            }
        });

        // 비밀번호 변경
        pwd_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (LinearLayout) View.inflate(MyidActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MyidActivity.this);
                dlg.setTitle("비밀번호 변경");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        current_pwd = findViewById(R.id.current_pwd);
                        new_pwd = findViewById(R.id.new_pwd);
                        renew_pwd = findViewById(R.id.renew_pwd);

                        String currentPwd = current_pwd.getText().toString();
                        String newPwd = new_pwd.getText().toString();
                        String renewPwd = renew_pwd.getText().toString();


                        Toast.makeText(MyidActivity.this, "비빌번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dlg.show();
            }
        });
    }
}
