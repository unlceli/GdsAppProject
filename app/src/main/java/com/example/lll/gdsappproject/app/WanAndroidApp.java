package com.example.lll.gdsappproject.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.lll.gdsappproject.BuildConfig;
import com.example.lll.gdsappproject.R;
import com.example.lll.gdsappproject.core.DataManager;
import com.example.lll.gdsappproject.core.dao.DaoMaster;
import com.example.lll.gdsappproject.core.dao.DaoSession;
import com.example.lll.gdsappproject.utils.logger.TxtFormatStrategy;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.squareup.leakcanary.RefWatcher;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class WanAndroidApp extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static WanAndroidApp instance;
    private RefWatcher refWatcher;
    public static boolean isFirstRun = true;

    private DaoSession mDaoSession;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SmartRefreshLayout.setDefaultRefreshFooterCreator(((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //指定Delivery header， 默认是内塞尔雷达 Header
            return (RefreshFooter) new DeliveryHeader(context);

        }));
        //默认是 BallPulseFooter
        SmartRefreshLayout.setDefaultRefreshFooterCreator(((context, layout) -> {
            return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }));
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static synchronized WanAndroidApp getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher(Context context) {
        WanAndroidApp androidApp = (WanAndroidApp) context.getApplicationContext();
        return androidApp.refWatcher;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        instance =this;



    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().
                    tag(getString(R.string.app_name)).build()));
        }
        //把log 存到本地
        Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder().tag(getString(R.string.app_name)).build(getPackageName(), getString(R.string.app_name))));
    }
}
