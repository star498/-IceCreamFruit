package com.example.estrellabarrientosmogollon.icecreamfruit.config.base;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
