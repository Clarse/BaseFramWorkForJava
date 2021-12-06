package com.example.contact.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contact.net.ApiService;
import com.example.contact.net.NetWorkManger;
import com.example.contact.net.error.ErrorResult;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public class BaseViewModel extends ViewModel {

    ApiService apiService;
    MutableLiveData<Boolean> isShowLoading = new MutableLiveData<>();
    MutableLiveData<ErrorResult> errorData = new MutableLiveData<>();

    public BaseViewModel() {
        apiService = NetWorkManger.getInstance().create(ApiService.class);
    }

    public void showLoading() {
        isShowLoading.setValue(true);
    }

    public void hideLoading() {
        isShowLoading.setValue(false);
    }

    public void showError(ErrorResult errorResult) {
        errorData.setValue(errorResult);
    }



}
