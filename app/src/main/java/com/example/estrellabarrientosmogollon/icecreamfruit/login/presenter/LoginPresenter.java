package com.example.estrellabarrientosmogollon.icecreamfruit.login.presenter;

import android.app.AlertDialog;
import android.text.Editable;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BasePresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.fragment.BaseFragmentPresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void login(String usuario, String pass);
    void crearCuenta(String usuario, String pass, String celular);
}
