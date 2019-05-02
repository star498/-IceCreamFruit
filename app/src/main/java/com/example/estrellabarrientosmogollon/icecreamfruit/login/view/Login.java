package com.example.estrellabarrientosmogollon.icecreamfruit.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.estrellabarrientosmogollon.icecreamfruit.R;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseHandler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseThreadPoolScheduler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BaseActivity;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.DataSourceLoginImpl;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.RepositoryLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioWrapper;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.presenter.LoginPresenter;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.presenter.LoginPresenterImpl;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase.UseCaseCrearCuenta;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase.UseCaseLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.Main;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    public String TAG = Login.class.getSimpleName();
    //no esta funcionando
    @BindView(R.id.txtusuario)
    EditText txtusuario;
    @BindView(R.id.txtcontraseña)
    EditText txtcontra;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected LoginPresenter getPresenter() {
        RepositoryLogin repositoryLogin= new RepositoryLogin(new DataSourceLoginImpl());
        presenter = new LoginPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new UseCaseCrearCuenta(repositoryLogin), new UseCaseLogin(repositoryLogin));
        return presenter;
    }

    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this );

    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return (ProgressBar) findViewById(R.id.progressbar);
    }

    public void showcrearCuenta(View view) {
       createCuentaDialog().show();

    }
    public AlertDialog createCuentaDialog() {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.main_registrar, null);
        final EditText user=(EditText)v.findViewById(R.id.txtuser);
        final EditText pass=(EditText)v.findViewById(R.id.txtpass);
        final EditText celular=(EditText)v.findViewById(R.id.txttelefono);
        final ProgressBar progressBar=(ProgressBar)v.findViewById(R.id.progressBar);
        Button btncrear=(Button)v.findViewById(R.id.crearcuenta) ;
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                presenter.crearCuenta(String.valueOf(user.getText()), String.valueOf(pass.getText()), String.valueOf(celular.getText()));
            }
        });
        builder.setView(v);
        alertDialog = builder.create();
        return alertDialog;
    }

    public void login(View view){
        String usuario=String.valueOf(((EditText)findViewById(R.id.txtusuario)).getText());
        String pass=String.valueOf(((EditText)findViewById(R.id.txtcontraseña)).getText());
        presenter.login(usuario, pass);
    }


    @Override
    public void startMain(UsuarioWrapper usuarioWrapper) {
        Intent intent = Main.launchActivity(getActivity(), usuarioWrapper);
        startActivity(intent);
    }


}
