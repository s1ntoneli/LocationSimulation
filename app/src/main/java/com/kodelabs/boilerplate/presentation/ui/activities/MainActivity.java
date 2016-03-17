package com.kodelabs.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.data.impl.CityQueryRepositoryImpl;
import com.kodelabs.boilerplate.domain.executor.MainThread;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.domain.interactors.impl.SampleInteractorImpl;
import com.kodelabs.boilerplate.presentation.presenters.MainPresenter.View;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

import junit.framework.Assert;

public class MainActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testQuery();
    }

    void testQuery() {
        ThreadExecutor.getInstance().execute(new AbstractInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance()) {
            @Override
            public void run() {
                CityQueryRepositoryImpl repository = new CityQueryRepositoryImpl();
                Assert.assertEquals(10, repository.query("北", 0, 10));
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
