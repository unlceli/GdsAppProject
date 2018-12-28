package com.example.lll.gdsappproject.contract.main;

import com.example.lll.gdsappproject.base.presenter.AbstractPresenter;
import com.example.lll.gdsappproject.base.view.AbstractView;
/**
 * @author quchao
 * @date 2018/5/4
 */
public interface RegisterContract {

    interface View extends AbstractView {

        /**
         * Show register data
         */
        void showRegisterSuccess();
    }

    interface Presenter extends AbstractPresenter<RegisterContract.View> {

        /**
         * 注册
         * http://www.wanandroid.com/user/register
         *
         * @param username user name
         * @param password password
         * @param rePassword re password
         */
        void getRegisterData(String username, String password, String rePassword);
    }
}
