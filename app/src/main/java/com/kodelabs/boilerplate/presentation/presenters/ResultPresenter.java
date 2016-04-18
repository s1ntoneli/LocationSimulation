package com.kodelabs.boilerplate.presentation.presenters;

import com.kodelabs.boilerplate.presentation.presenters.base.BasePresenter;
import com.kodelabs.boilerplate.presentation.ui.BaseView;

/**
 * Created by lixindong on 16/3/18.
 */
public interface ResultPresenter extends BasePresenter {
    interface View extends BaseView {
        void showText(String text);
        void showInfo(String[] data);
    }

}
