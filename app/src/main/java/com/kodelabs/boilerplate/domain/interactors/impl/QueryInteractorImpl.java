package com.kodelabs.boilerplate.domain.interactors.impl;

import com.kodelabs.boilerplate.data.CityQueryRepository;
import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.interactors.QueryInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;

/**
 * Created by lixindong on 16/3/18.
 */
public class QueryInteractorImpl extends AbstractInteractor implements QueryInteractor {
    CityQueryRepository mRepository;
    Callback mCallback;
    public QueryInteractorImpl(Executor threadExecutor, MainThread mainThread, Callback callback, CityQueryRepository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        String[] result = mRepository.query("北", 1, 10);
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
