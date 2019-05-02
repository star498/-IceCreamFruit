package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity;

import android.support.annotation.ColorRes;


/**
 * Created by @stevecampos on 15/01/2018.
 */

public interface View extends BaseView<Presenter> {
    void changeBackground(@ColorRes int color);
}
