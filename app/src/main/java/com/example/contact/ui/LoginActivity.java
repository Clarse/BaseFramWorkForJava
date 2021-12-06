package com.example.contact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.contact.base.BaseActivity;
import com.example.contact.base.BaseViewModel;
import com.example.contact.database.AppDataBase;
import com.example.contact.databinding.ActivityLoginBinding;
import com.example.contact.entity.User;
import com.example.contact.utils.Utils;
import com.tencent.mmkv.MMKV;

public class LoginActivity extends BaseActivity<BaseViewModel, ActivityLoginBinding> {

    private final MMKV mmkv = MMKV.defaultMMKV();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public Boolean isNeedEventBus() {
        return false;
    }

    public void initView() {

    }

    @Override
    public void initClick() {
        vb.tvRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        vb.btnLogin.setOnClickListener(v -> {
            if (CheckData()) {
                startActivity(new Intent(this, MainActivity.class));
                mmkv.encode("user_name", vb.etInputUserName.getText().toString());
            }
        });
    }

    private boolean CheckData() {
        if (TextUtils.isEmpty(vb.etInputUserName.getText().toString())) {
            Utils.showShortToast(this, "请输入用户名");
            return false;
        }
        if (TextUtils.isEmpty(vb.etInputPassword.getText().toString())) {
            Utils.showShortToast(this, "请输入密码");
            return false;
        }
        AppDataBase dataBase = AppDataBase.getInstance(this);
        User user = dataBase.userDao().getUser(vb.etInputUserName.getText().toString(), vb.etInputPassword.getText().toString());
        if (user == null) {
            Utils.showShortToast(this, "用户不存在！");
            return false;
        }
        return true;
    }

    @Override
    public void initVM() {

    }

}
