package com.example.lll.gdsappproject.di.component;


import com.example.lll.gdsappproject.app.WanAndroidApp;
import com.example.lll.gdsappproject.core.DataManager;
import com.example.lll.gdsappproject.di.module.AbstractAllActivityModule;
import com.example.lll.gdsappproject.di.module.AbstractAllDialogFragmentModule;
import com.example.lll.gdsappproject.di.module.AbstractAllFragmentModule;
import com.example.lll.gdsappproject.di.module.AppModule;
import com.example.lll.gdsappproject.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author quchao
 * @date 2017/11/27
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AbstractAllDialogFragmentModule.class,
        AppModule.class,
        HttpModule.class})
public interface AppComponent {

    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    void inject(WanAndroidApp wanAndroidApp);

    /**
     * 提供App的Context
     *
     * @return GeeksApp context
     */
    WanAndroidApp getContext();

    /**
     * 数据中心
     *
     * @return DataManager
     */
    DataManager getDataManager();

}
