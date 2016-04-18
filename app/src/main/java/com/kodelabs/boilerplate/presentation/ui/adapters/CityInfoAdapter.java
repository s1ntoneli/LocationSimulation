package com.kodelabs.boilerplate.presentation.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVObject;
import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.presentation.ui.adapters.base.AdapterBase;
import com.kodelabs.boilerplate.presentation.ui.adapters.base.ViewHolderHelper;

import java.util.List;

/**
 * Created by lixindong on 16/4/18.
 */
public class CityInfoAdapter extends AdapterBase<AVObject> {
    public CityInfoAdapter(Context context, int[] layoutResArrays) {
        super(context, layoutResArrays);
    }

    public CityInfoAdapter(Context context, int[] layoutResArrays, List<AVObject> data) {
        super(context, layoutResArrays, data);
    }

    @Override
    protected void convert(ViewHolderHelper helper, AVObject item) {
        StringBuilder builder = new StringBuilder();
        builder.append(item.get("area_cn"));
        builder.append(",");
        builder.append(item.get("city"));
        helper.setText(R.id.city_name, builder.toString());

        helper.setImageFromResId(R.id.icon_type, R.drawable.icon_global);
        helper.setText(R.id.coodinate, item.get("city_id").toString());
    }

    @Override
    protected ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent) {
        return ViewHolderHelper.get(mContext, convertView, parent, R.layout.city_info_item ,position);
    }
}
