package com.example.estrellabarrientosmogollon.icecreamfruit.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.estrellabarrientosmogollon.icecreamfruit.R;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseHandler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseThreadPoolScheduler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseActivity;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioWrapper;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.view.Login;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.presenter.MainPresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.presenter.MainPresenterImpl;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.view.MainView;


public class Main extends BaseActivity<MainView, MainPresenter> implements MainView{
    private String TAG= Main.class.getSimpleName();


    public static Intent launchActivity(Context context, UsuarioWrapper usuarioWrapper) {
        Intent intent = new Intent(context, Main.class);
        intent.putExtras(usuarioWrapper.convertBundle(new Bundle()));
        return intent;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MainPresenter getPresenter() {
        presenter = new MainPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
        return presenter;
    }

    @Override
    protected MainView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.main_activity);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void startLogin() {
      Intent intent=new Intent(this, Login.class);
      startActivity(intent);
    }
    public void logout(View view){
        presenter.logout();
    }
}
