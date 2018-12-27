package com.example.lll.gdsappproject.di.component;

import com.example.lll.gdsappproject.app.WanAndroidApp;
import com.example.lll.gdsappproject.core.DataManager;
import com.example.lll.gdsappproject.di.module.AbstractAllActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class})
public interface AppComponent {
    /**
     * 注入 WanAndroidApp
     *
     * @param wanAndroidApp
     */
    void inject(WanAndroidApp wanAndroidApp);

    /**
     * 提供 AppContext
     *
     * @return
     */
    WanAndroidApp getContext();

    /**
     * 数据中心
     *
     * @return
     */
    DataManager getDataManager();
}
