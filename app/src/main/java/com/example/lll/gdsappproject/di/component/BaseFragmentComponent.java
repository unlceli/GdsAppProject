package com.example.lll.gdsappproject.di.component;

import com.example.lll.gdsappproject.base.fragment.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author quchao
 * @date 2018/5/3
 */

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {

    /**
     * 每一个继承于BaseFragment的Fragment都继承于同一个子组件
     */
    @Subcomponent.Builder
    abstract class BaseBuilder extends AndroidInjector.Builder<BaseFragment>{

    }
}
