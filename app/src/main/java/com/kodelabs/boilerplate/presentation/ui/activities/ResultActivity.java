package com.kodelabs.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.presentation.presenters.ResultPresenter;
import com.kodelabs.boilerplate.presentation.presenters.impl.ResultPresenterImpl;
import com.kodelabs.boilerplate.presentation.ui.adapters.CityInfoAdapter;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

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
    public void showInfo(String[] data) {
        ArrayList<AVObject> list = new ArrayList<>();
        for (String item : data) {
            try {
                AVObject object = AVObject.parseAVObject(item);
                list.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mAdapter.setData(list);
    }

    void initListView() {
        mListView = (ListView) findViewById(R.id.lv_result);
        mAdapter = new CityInfoAdapter(this, null);
        mListView.setAdapter(mAdapter);
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
    ListView mListView;
    CityInfoAdapter mAdapter;

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
        initListView();
        mPresenter = new ResultPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
    }
}
