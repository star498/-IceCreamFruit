package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity;

import android.os.Bundle;


/**
 * Created by @stevecampos on 26/01/2018.
 */

public interface BasePresenter<T extends BaseView>  extends com.example.estrellabarrientosmogollon.icecreamfruit.config.base.BasePresenter<T>{
    void setExtras(Bundle extras);
    void onSingleItemSelected(Object singleItem, int selectedPosition);

}
