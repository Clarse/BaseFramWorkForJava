package com.example.contact.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.annotation.Nullable;

import com.example.contact.R;
import com.example.contact.base.BaseActivity;
import com.example.contact.base.BaseViewModel;
import com.example.contact.database.AppDataBase;
import com.example.contact.databinding.ActivityRegisterBinding;
import com.example.contact.entity.User;
import com.example.contact.utils.Utils;

public class RegisterActivity extends BaseActivity<BaseViewModel, ActivityRegisterBinding> {

    private boolean canRegister = false;
    private final String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16})";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public Boolean isNeedEventBus() {
        return false;
    }

    @Override
    public void initView() {
        vb.toolbar.setNavigationOnClickListener(view -> finish());
        vb.etInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Utils.Match(regex, vb.etInputPassword.getText().toString())) {
                    vb.tvWarning.setText(R.string.pass_regex);
                } else if (!TextUtils.isEmpty(vb.etInputPasswordRepeat.getText().toString()) &&
                        !vb.etInputPassword.getText().toString().equals(vb.etInputPasswordRepeat.getText().toString())) {
                    vb.tvWarning.setText("两次密码不一致");
                    canRegister = false;
                } else {
                    vb.tvWarning.setText(null);
                    canRegister = true;
                }
            }
        });
        vb.etInputPasswordRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Utils.Match(regex, vb.etInputPasswordRepeat.getText().toString())) {
                    vb.tvWarning.setText(R.string.pass_regex);
                } else if (!TextUtils.isEmpty(vb.etInputPassword.getText().toString()) &&
                        !vb.etInputPassword.getText().toString().equals(vb.etInputPasswordRepeat.getText().toString())) {
                    vb.tvWarning.setText("两次密码不一致");
                    canRegister = false;
                } else {
                    vb.tvWarning.setText(null);
                    canRegister = true;
                }
            }
        });
    }

    @Override
    public void initClick() {
        vb.btnRegister.setOnClickListener(v -> {
            if (canRegister) {
                AppDataBase dataBase = AppDataBase.getInstance(this);
                User user = new User(vb.etInputUserName.getText().toString(), vb.etInputPassword.getText().toString());
                dataBase.userDao().insertUser(user);
            }
        });
    }

    @Override
    public void initVM() {

    }

}
