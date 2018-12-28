package com.example.lll.gdsappproject.contract.main;

import com.example.lll.gdsappproject.base.presenter.AbstractPresenter;
import com.example.lll.gdsappproject.base.view.AbstractView;

public interface SplashContract {
    interface View extends AbstractView {
        /**
         * after some time jump to main page;
         */
        void jumpToMain();
    }

    interface Presenter extends AbstractPresenter<View> {

    }
}
