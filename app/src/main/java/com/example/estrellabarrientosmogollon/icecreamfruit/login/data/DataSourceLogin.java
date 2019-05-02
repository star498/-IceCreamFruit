package com.example.estrellabarrientosmogollon.icecreamfruit.login.data;

import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;

public interface DataSourceLogin {

    interface Callback <T>{
        void onLoad(boolean success, T item);
    }

    void createAccount(String usuario, String pass, int tipo,String celular, Callback<UsuarioUi>callback);
    void login(String usuario, String pass, Callback<UsuarioUi>callback);
}
