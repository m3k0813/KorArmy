package com.example.korarmy.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.korarmy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {
    private EditText editText_email;
    private EditText editText_name;
    private EditText editText_pwd;
    private Button confirmbtn;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editText_email = findViewById(R.id.editText_email);
        editText_name = findViewById(R.id.editText_name);
        editText_pwd = findViewById(R.id.editText_pwd);
        confirmbtn = findViewById(R.id.confirmbtn);
        firebaseAuth = firebaseAuth.getInstance();

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editText_email.getText().toString().trim();
                final String pwd = editText_pwd.getText().toString().trim();
                final String username = editText_name.getText().toString().trim();
            firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Intent intent = new Intent(SignupActivity.this, ArmyInfoActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(SignupActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
    });

}
}