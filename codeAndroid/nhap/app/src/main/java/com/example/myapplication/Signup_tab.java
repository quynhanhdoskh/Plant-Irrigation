package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_tab extends AppCompatActivity {
    private EditText Email, TelNumber, Address, Password;
    private Button ButtonSignup;
    private FirebaseAuth auth;
    TextView tLi;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_tab);


        auth = FirebaseAuth.getInstance();
        ButtonSignup = (Button) findViewById(R.id.buttonSignup);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.pass);
        TelNumber = (EditText) findViewById(R.id.telnum);
        Address = (EditText) findViewById(R.id.addr);
        tLi = findViewById(R.id.toLogin);

        //chuyển trang từ đăng ký sang đăng nhập
        tLi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent mh2 = new Intent(Signup_tab.this, Login_tab.class);
                startActivity(mh2);
            }
        });

        //đăng ký
        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String telnum = TelNumber.getText().toString().trim();
                String addr = Address.getText().toString().trim();

                //kiểm tra email
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Hãy nhập email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //kiểm tra mật khẩu
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Hãy nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, cần có ít nhất 6 ký tự!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //kiểm tra số điện thoại
                if (TextUtils.isEmpty(telnum)) {
                    Toast.makeText(getApplicationContext(), "Hãy nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (telnum.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Số điện thoại không đúng!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //kiểm tra địa chỉ
                if (TextUtils.isEmpty(addr)) {
                    Toast.makeText(getApplicationContext(), "Hãy nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup_tab.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Signup_tab.this, "đăng ký" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup_tab.this, "Đăng ký thất bại!" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Signup_tab.this, Login_tab.class));
                                    finishAffinity();
                                }
                            }
                        });

            }
        });

    }

}