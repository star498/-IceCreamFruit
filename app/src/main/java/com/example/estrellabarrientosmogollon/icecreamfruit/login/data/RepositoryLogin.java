package com.example.estrellabarrientosmogollon.icecreamfruit.login.data;

import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;

public class RepositoryLogin implements DataSourceLogin{

    DataSourceLoginImpl dataSourceLoginImpl;

    public RepositoryLogin(DataSourceLoginImpl dataSourceLoginImpl) {
        this.dataSourceLoginImpl = dataSourceLoginImpl;
    }

    @Override
    public void createAccount(String usuario, String pass, int tipo, Callback<UsuarioUi>callback) {
         dataSourceLoginImpl.createAccount(usuario, pass, tipo, callback);
    }

    @Override
    public void login(String usuario, String pass, Callback<UsuarioUi> callback) {
        dataSourceLoginImpl.login(usuario,pass, callback);
    }

}
