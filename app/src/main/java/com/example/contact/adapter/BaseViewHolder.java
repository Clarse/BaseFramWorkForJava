package com.example.contact.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public ViewBinding v;

    public BaseViewHolder(View itemView, ViewBinding v) {
        super(itemView);
        this.v = v;
    }

}
