package com.example.estrellabarrientosmogollon.icecreamfruit.config.base.fragment;


import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BasePresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseView;

/**
 * Created by @stevecampos on 14/02/2018.
 */

public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onAttach();
    void onCreateView();
    void onViewCreated();
    void onActivityCreated();
    void onDestroyView();
    void onDetach();
}
