package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

public class SplashActivity extends AppCompatActivity {

    MMKV mmkv = MMKV.defaultMMKV();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> {
            if (mmkv.decodeString("user_name") == null) {
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        }, 500);
    }

}
