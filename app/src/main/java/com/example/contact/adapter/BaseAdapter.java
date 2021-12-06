package com.example.contact.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * @author: YH
 * @date: 2021/12/3
 * @desc:
 */
public abstract class BaseAdapter<VB extends ViewBinding, T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private ArrayList<T> listData;

    private onItemClickListener itemClickListener;
    private onItemLongClickListener itemLongClickListener;

    public BaseAdapter(Context mContext, ArrayList<T> listData) {
        this.mContext = mContext;
        this.listData = listData;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class<VB> clazz = (Class<VB>) type.getActualTypeArguments()[0];
        VB vb = null;
        try {
            Method method = clazz.getMethod("inflate", LayoutInflater.class);
            vb = (VB) method.invoke(null, LayoutInflater.from(mContext));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new BaseViewHolder(vb.getRoot(), vb);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setOnClickListener(view -> itemClickListener.itemClick(position));
        holder.itemView.setOnLongClickListener(view -> {
            itemLongClickListener.itemLongClick(position);
            return true;
        });
        convert((VB) holder.v, listData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public interface onItemClickListener {
        void itemClick(int position);
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface onItemLongClickListener {
        void itemLongClick(int position);
    }

    public void setItemLongClickListener(onItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    abstract void convert(VB v, T t, Integer position);

}
