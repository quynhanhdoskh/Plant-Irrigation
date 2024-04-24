package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ControlActivity extends AppCompatActivity {
    ImageView toSetting;
    ImageView toUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_activity);

        //chuyển trang sang cài đặt
        toSetting = findViewById(R.id.setting);
        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh2 = new Intent(ControlActivity.this, SettingActivity.class);
                startActivity(mh2);
            }
        });

        //chuyển trang sang thông tin user
        toUser = findViewById(R.id.imageView4);
        toUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh3 = new Intent(ControlActivity.this, UserActivity.class);
                startActivity(mh3);
            }
        });
    }
}
