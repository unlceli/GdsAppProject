package com.example.lll.gdsappproject.contract.main;

import com.example.lll.gdsappproject.base.presenter.AbstractPresenter;
import com.example.lll.gdsappproject.base.view.AbstractView;

/**
 * @author quchao
 * @date 2018/2/26
 */

public interface LoginContract {

    interface View extends AbstractView {

        /**
         * Show login data
         *
         */
        void showLoginSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get Login data
         *
         * @param username user name
         * @param password password
         */
        void getLoginData(String username, String password);
    }
}
