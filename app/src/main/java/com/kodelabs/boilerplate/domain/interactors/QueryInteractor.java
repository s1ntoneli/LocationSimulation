package com.kodelabs.boilerplate.domain.interactors;

import com.kodelabs.boilerplate.domain.interactors.base.Interactor;

/**
 * Created by lixindong on 16/3/18.
 */
public interface QueryInteractor extends Interactor {
    interface Callback {
        void showResult(String[] data);
        void showError(String data);
    }
}
