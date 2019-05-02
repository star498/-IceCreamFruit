package com.example.estrellabarrientosmogollon.icecreamfruit.main.view;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.presenter.MainPresenter;

public interface MainView  extends BaseView<MainPresenter> {
    void startLogin();
}