package com.kodelabs.boilerplate.presentation.ui.adapters.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindong on 16/4/18.
 */
public abstract class AdapterBase <T> extends BaseAdapter {
    protected final Context mContext;
    protected List<T> mData;
    protected final int [] mLayoutResArrays;

    public AdapterBase(Context context, int [] layoutResArrays){
        this(context, layoutResArrays, null);
    }

    public AdapterBase(Context context, int [] layoutResArrays, List<T> data){
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mContext = context;
        this.mLayoutResArrays = layoutResArrays;
    }

    public void setData(ArrayList<T> data){
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void addData(ArrayList<T> data){
        if(data != null){
            this.mData.addAll(data);
        }

        this.notifyDataSetChanged();
    }

    public void addData(T data) {
        this.mData.add(data);
        this.notifyDataSetChanged();
    }

    public ArrayList<T> getAllData() {
        return (ArrayList<T>)this.mData;
    }

    @Override
    public int getCount() {
        if(this.mData == null){
            return 0;
        }
        return this.mData.size();
    }

    @Override
    public T getItem(int position) {
        if(position > this.mData.size()){
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolderHelper helper = getAdapterHelper(position, convertView, parent);
        T item = getItem(position);
        convert(helper, item);
        return helper.getView();
    }

    protected abstract void convert(ViewHolderHelper helper, T item);
    protected abstract ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent);
}