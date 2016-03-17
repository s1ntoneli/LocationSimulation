package com.kodelabs.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.presentation.presenters.ResultPresenter;
import com.kodelabs.boilerplate.presentation.presenters.impl.ResultPresenterImpl;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

/**
 * Created by lixindong on 16/3/18.
 */
public class ResultActivity extends AppCompatActivity implements ResultPresenter.View {
    ResultPresenter mPresenter;
    @Override
    public void showText(String text) {
        mResult.setText(text);
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

    TextView mResult;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mResult = (TextView) findViewById(R.id.result);
        mPresenter = new ResultPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
    }
}
