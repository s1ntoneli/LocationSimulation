package com.kodelabs.boilerplate.data.impl;

import android.util.Log;

import com.avos.avoscloud.AVCloud;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FunctionCallback;
import com.avos.avoscloud.ObjectValueFilter;
import com.avos.avoscloud.PaasClient;
import com.kodelabs.boilerplate.LeanCloudConstants;
import com.kodelabs.boilerplate.data.CityQueryRepository;
import com.kodelabs.boilerplate.domain.model.SampleModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by lixindong on 16/3/17.
 */
public class CityQueryRepositoryImpl implements CityQueryRepository {
    private static final String TAG = CityQueryRepositoryImpl.class.getSimpleName();

    @Override
    public String[] query(String keyword, int page, int pageSize) {
        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstants.TABLE_CITY_LOCAL);
        query.whereMatches("area_cn", ".*"+keyword+".*");
        query.limit(pageSize);
        query.skip(page*pageSize);
        try {
            return formatAVObjectList(query.find());
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] formatAVObjectList(List<AVObject> objects) {
        if (objects == null || objects.size() == 0)
            return null;
        String[] result = new String[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            result[i] = objects.get(i).toString();
            Log.d(TAG, result[i]);
        }
        return result;
    }

    @Override
    public String[] queryRandom(int number) {
        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstants.TABLE_CITY_LOCAL);
        try {

            int count = query.count();
            Integer[] ids = generateRandomIds(number, count);
            List<AVObject> result = new ArrayList<>();
            for (int i = 0 ; i < ids.length; i++) {
                query.skip(ids[i]);
                AVObject object = query.getFirst();
                result.add(object);
            }
            return formatAVObjectList(result);
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer[] generateRandomIds(int number, int count) {
        HashSet<Integer> hashSet = new HashSet<>();
        Integer[] result = new Integer[number];
        while (hashSet.size() < number) {
            int num = (int) Math.round(Math.random()*(count - 2));
            Log.d(TAG, "random " + num);
            hashSet.add(num);
        }
        hashSet.toArray(result);
        return result;
    }

    @Override
    public String[] queryHot(int pageSize, int page) {
        AVQuery<AVObject> query = new AVQuery<>(LeanCloudConstants.TABLE_CITY_LOCAL);
        query.orderByDescending("use_times");
        query.skip(page * pageSize);
        query.limit(pageSize);
        try {
            return formatAVObjectList(query.find());
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(SampleModel model) {
        return false;
    }

    @Override
    public boolean update(SampleModel model) {
        return false;
    }

    @Override
    public SampleModel get(Object id) {
        return null;
    }

    @Override
    public boolean delete(SampleModel model) {
        return false;
    }
}
