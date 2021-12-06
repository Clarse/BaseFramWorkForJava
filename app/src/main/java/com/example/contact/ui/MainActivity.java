package com.example.contact.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contact.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        initView();
    }

    private void initView() {

    }

}