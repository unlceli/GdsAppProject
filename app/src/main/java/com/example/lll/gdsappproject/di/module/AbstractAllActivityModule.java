package com.example.lll.gdsappproject.di.module;

import com.example.lll.gdsappproject.MainActivity;
import com.example.lll.gdsappproject.di.component.BaseActivityComponent;
import com.example.lll.gdsappproject.ui.main.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * @author quchao
 * @date 2018/5/3
 */

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributesSplashActivityInjector();




}
