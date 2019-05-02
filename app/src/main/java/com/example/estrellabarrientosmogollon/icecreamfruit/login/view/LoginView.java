package com.example.estrellabarrientosmogollon.icecreamfruit.login.view;

import android.app.AlertDialog;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BasePresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioWrapper;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.presenter.LoginPresenter;

public interface LoginView extends BaseView<LoginPresenter> {

    void startMain(UsuarioWrapper usuarioWrapper);
}
