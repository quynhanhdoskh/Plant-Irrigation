package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    ImageView toControl;
    ImageView toUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        //chuyển trang về điều khiển
        toControl = findViewById(R.id.button_back);
        toControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh2 = new Intent(SettingActivity.this, ControlActivity.class);
                startActivity(mh2);
            }
        });

        //chuyển trang sang thông tin user
        toUser = findViewById(R.id.imageView4);
        toUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mh3 = new Intent(SettingActivity.this, UserActivity.class);
                startActivity(mh3);
            }
        });
    }
}
