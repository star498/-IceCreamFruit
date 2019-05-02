package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity;

import java.util.List;

/**
 * Created by @stevecampos on 17/01/2018.
 */

public interface BaseView<T extends BasePresenter> extends com.example.estrellabarrientosmogollon.icecreamfruit.config.base.BaseView<T> {
    void showProgress();

    void hideProgress();

    void showMessage(CharSequence message);

    void showImportantMessage(CharSequence message);

    void showFinalMessage(CharSequence message);

    void showListSingleChooser(String title, List<Object> items, int positionSelected);


}
