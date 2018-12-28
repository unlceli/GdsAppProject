package com.example.lll.gdsappproject.presenter;

import com.example.lll.gdsappproject.base.presenter.BasePresenter;
import com.example.lll.gdsappproject.contract.main.SplashContract;
import com.example.lll.gdsappproject.core.DataManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {


    @Inject
    SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
        long splashTime = 10000;
        addSubscribe(Observable.timer(splashTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> view.jumpToMain()));
    }
}
