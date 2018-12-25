package com.example.lll.gdsappproject.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractSimpleActivity extends SupportActivity {
    private Unbinder unbinder;
    protected AbstractSimpleActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder =ButterKnife.bind(this);
        mActivity = this;
    }

    protected abstract int getLayoutId();
}
