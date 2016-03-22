package com.kodelabs.boilerplate.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.avos.avoscloud.AVObject;
import com.kodelabs.boilerplate.R;
import com.kodelabs.boilerplate.data.impl.CityQueryRepositoryImpl;
import com.kodelabs.boilerplate.domain.executor.impl.ThreadExecutor;
import com.kodelabs.boilerplate.domain.interactors.base.AbstractInteractor;
import com.kodelabs.boilerplate.presentation.presenters.MainPresenter.View;
import com.kodelabs.boilerplate.threading.MainThreadImpl;

public class MainActivity extends AppCompatActivity implements View {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testQuery();
    }

    void testQuery() {
        ThreadExecutor.getInstance().execute(new AbstractInteractor(ThreadExecutor.getInstance(), MainThreadImpl.getInstance()) {
            @Override
            public void run() {
                CityQueryRepositoryImpl repository = new CityQueryRepositoryImpl();
//                Assert.assertEquals(10, repository.query("北", 0, 10));
                String[] result = repository.queryHot(10, 2);
                    try {
                        for (int i = 0; i < result.length; i++) {
                            AVObject object = AVObject.parseAVObject(result[i]);
                            object.increment("use_times");
                            object.saveInBackground();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                for (String str : result) {
                    Log.d(TAG, "str " + str);
                }
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
