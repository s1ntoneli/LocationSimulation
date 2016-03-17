package com.kodelabs.boilerplate.data.impl;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.kodelabs.boilerplate.LeanCloudConstants;
import com.kodelabs.boilerplate.data.CityQueryRepository;
import com.kodelabs.boilerplate.domain.model.SampleModel;

import java.util.List;

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
        return null;
    }

    @Override
    public String[] queryHot(int number) {
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
