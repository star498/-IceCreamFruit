package com.example.estrellabarrientosmogollon.icecreamfruit.main.presenter;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BasePresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.view.MainView;

public interface MainPresenter extends BasePresenter<MainView> {
    void logout();
}
