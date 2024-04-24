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

public class Login_tab extends AppCompatActivity {
    TextView tSu;
    Button ButtonLogin;
    EditText Email, Password;
    FirebaseAuth auth;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("user");
    //public String Tk , Mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tab);
//
        auth = FirebaseAuth.getInstance();
        //nếu đăng nhập rồi thì vào thẳng trang điều khiển
        if (auth.getCurrentUser() != null) {
            Toast.makeText(Login_tab.this, "Tự động đăng nhập" , Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login_tab.this, ControlActivity.class));
            finish();
        }

        //chuyển trang sang đăng ký
        tSu = findViewById(R.id.toSignup);
        tSu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent mh2 = new Intent(Login_tab.this, Signup_tab.class);
                startActivity(mh2);
            }
        });

        //ấn nút đăng nhập
        ButtonLogin = (Button) findViewById(R.id.buttonLogin);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.pass);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

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

                //đăng nhập bằng email và mật khẩu
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_tab.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Login_tab.this, ControlActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                    // there was an error
                                } else {
                                    Toast.makeText(Login_tab.this, "Tài khoản hoặc mật khẩu sai!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        //forgot pass
        String emailAddress = "quynhanhdo07052002@gmail.com";

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_tab.this, "Email sent",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //getUser();
        //Button button = findViewById(R.id.button);

    }
    /*
    private void getUser(){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // get id and pass from Firebase
                Tk = snapshot.child("id").getValue(String.class);
                Mk = snapshot.child("pass").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

}