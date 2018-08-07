package com.cxh.mylogin.di.component;

import com.cxh.mylogin.di.module.LoginModule;
import com.cxh.mylogin.mvp.ui.activity.LoginActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}