package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.data.CityQueryRepository;
import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.QueryInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;

import java.util.HashMap;

/**
 * Created by lixindong on 16/3/18.
 */
public class QueryInteractorImpl extends AbstractInteractor implements QueryInteractor {
    CityQueryRepository mRepository;
    Callback mCallback;
    HashMap<String, String> mParams;
    public QueryInteractorImpl(Executor threadExecutor, MainThread mainThread, Callback callback, CityQueryRepository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void setParams(HashMap<String, String> params) {
        mParams = params;
    }

    @Override
    public void addParam(String key, String value) {
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        mParams.put(key, value);
    }

    private String getParam(String key) {
        String value = "";
        if (mParams != null && mParams.containsKey(key)) {
            value = mParams.get(key);
        }
        return value;
    }

    private int getIntParam(String key, int defaultValue) {
        int value = defaultValue;
        String strValue = getParam(key);
        if (strValue != "") {
            value = Integer.valueOf(strValue);
        }
        return value;
    }

    @Override
    public void run() {
        String[] result = null;
        switch (getIntParam("mode", 0)) {
            case CityQueryRepository.MODE_NORMAL:
                result = mRepository.query("北", 1, 10);
                break;
            case CityQueryRepository.MODE_RANDOM:
                result = mRepository.queryRandom(getIntParam("number", 10));
                break;
            case CityQueryRepository.MODE_HOT:
                result = mRepository.queryHot(getIntParam("pageSize", 10), getIntParam("page", 0));
                break;
        }
        if (result == null || result.length == 0) {
            sendError("result is null");
        } else {
            postMessage(result);
        }
    }

    private void postMessage(final String[] message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.showResult(message);
                }
            }
        });
    }
    private void sendError(final String data) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.showError(data);
                }
            }
        });
    }
}
