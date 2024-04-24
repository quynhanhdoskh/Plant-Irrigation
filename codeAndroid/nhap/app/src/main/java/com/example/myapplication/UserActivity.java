package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    ImageView toControl2;
    private TextView LogOut, tvEmail, tvSdt, tvDiachi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        //chuyển trang về điều khiển
        toControl2 = findViewById(R.id.back_to_ctrl);
        toControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh2 = new Intent(UserActivity.this, ControlActivity.class);
                startActivity(mh2);
            }
        });

        //get thông tin từ Fb về để hiển thị
        tvEmail = findViewById(R.id.tvEmail);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        String email = user.getEmail();
        tvEmail.setText(email);

        //nhấn đăng xuất
        LogOut = findViewById(R.id.log_out);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserActivity.this, "Đăng xuất", Toast.LENGTH_LONG).show();
                Intent mh2 = new Intent(UserActivity.this, Login_tab.class);
                startActivity(mh2);
            }
        });
    }
}
