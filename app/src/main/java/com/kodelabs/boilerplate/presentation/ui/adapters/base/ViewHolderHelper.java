package com.kodelabs.boilerplate.presentation.ui.adapters.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lixindong on 16/4/18.
 */
public class ViewHolderHelper {

    private SparseArray<View> mViews;
    private Context mContext;
    private int position;
    private View mConvertView;

    private ViewHolderHelper(Context context, ViewGroup parent, int layoutId, int position) {
        this.mContext = context;
        this.position = position;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolderHelper get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolderHelper(context, parent, layoutId, position);
        }
        ViewHolderHelper existingHelper = (ViewHolderHelper) convertView.getTag();
        existingHelper.position = position;
        return existingHelper;
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolderHelper setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public ViewHolderHelper setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public ViewHolderHelper setImageFromResId(int viewId, int resId) {
        ImageView imageView = findViewById(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolderHelper setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public View getView() {
        return mConvertView;
    }
}