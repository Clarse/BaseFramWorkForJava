package com.example.contact.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.contact.event.EventCode;
import com.example.contact.event.EventMessage;
import com.example.contact.net.error.ErrorResult;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * @author: YH
 * @date: 2021/12/2
 * @desc:
 */
public abstract class BaseActivity<VM extends BaseViewModel, VB extends ViewBinding> extends AppCompatActivity {

    public VM vm;
    public VB vb;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<BaseViewModel> clazz1 = (Class<BaseViewModel>) type.getActualTypeArguments()[0];
        vm = (VM) new ViewModelProvider(this).get(clazz1);
        Class<ViewBinding> clazz2 = (Class<ViewBinding>) type.getActualTypeArguments()[1];
        System.out.println(clazz2);
        try {
            Method method = clazz2.getMethod("inflate", LayoutInflater.class);
            vb = (VB) method.invoke(null, getLayoutInflater());
            setContentView(vb.getRoot());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        init();
        initView();
        initClick();
        initVM();
    }

    private void init() {
        if (isNeedEventBus()) {
            EventBus.getDefault().register(this);
        }
        vm.isShowLoading.observe(this, aBoolean -> {
            if (aBoolean) {
                showLoading();
            } else {
                dismissLoading();
            }
        });
        vm.errorData.observe(this, new Observer<ErrorResult>() {
            @Override
            public void onChanged(ErrorResult errorResult) {

            }
        });
    }

    private void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new ProgressDialog(this);
        }
        loadingDialog.show();
    }

    private void dismissLoading() {
        loadingDialog.dismiss();
        loadingDialog = null;
    }

    public abstract Boolean isNeedEventBus();

    public void handleEvent(@NonNull EventMessage msg) {
        if (msg.getCode() == EventCode.LOGIN_OUT) {
            finish();
        }
    }

    public abstract void initView();

    public abstract void initClick();

    public abstract void initVM();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isNeedEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

}
