package com.kodelabs.boilerplate.presentation.presenters.impl;

import com.avos.avoscloud.AVObject;
import com.kodelabs.boilerplate.data.CityQueryRepository;
import com.kodelabs.boilerplate.data.impl.CityQueryRepositoryImpl;
import com.kodelabs.boilerplate.domain.executor.Executor;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.domain.interactors.QueryInteractor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.domain.interactors.impl.QueryInteractorImpl;
import com.kodelabs.boilerplate.presentation.presenters.ResultPresenter;
import com.kodelabs.boilerplate.presentation.presenters.base.AbstractPresenter;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

/**
 * Created by lixindong on 16/3/18.
 */
public class ResultPresenterImpl extends AbstractPresenter implements ResultPresenter, QueryInteractor.Callback {
    View mView;
    public ResultPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        mView = view;
        mRepository = new CityQueryRepositoryImpl();
        mInteractor = new QueryInteractorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, mRepository);
    }
    CityQueryRepository mRepository;
    AbstractInteractor mInteractor;

    @Override
    public void resume() {
        mInteractor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showResult(String[] data) {
        if (data == null || data.length == 0) {
            showError("data is empty");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            try {
                AVObject object = AVObject.parseAVObject(data[i]);
                builder.append(object.get("area_cn"));
                builder.append(",");
                builder.append(object.get("city"));
                builder.append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mView.showText(builder.toString());
    }

    @Override
    public void showError(String data) {
        mView.showText(data);
    }
}
