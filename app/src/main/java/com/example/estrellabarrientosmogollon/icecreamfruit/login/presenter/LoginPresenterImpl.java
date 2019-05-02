package com.example.estrellabarrientosmogollon.icecreamfruit.login.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCase;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseHandler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.fragment.BaseFragmentPresenterImpl;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioWrapper;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase.UseCaseCrearCuenta;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase.UseCaseLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.view.LoginView;

import java.util.Random;

public class LoginPresenterImpl extends BaseFragmentPresenterImpl<LoginView> implements LoginPresenter {

    private String TAG=LoginPresenterImpl.class.getSimpleName();
    UseCaseCrearCuenta useCaseCrearCuenta;
    UseCaseLogin useCaseLogin;

    public LoginPresenterImpl(UseCaseHandler handler, Resources res, UseCaseCrearCuenta useCaseCrearCuenta, UseCaseLogin useCaseLogin) {
        super(handler, res);
        this.useCaseCrearCuenta = useCaseCrearCuenta;
        this.useCaseLogin=useCaseLogin;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
    }

    @Override
    public void login(String usuario, String pass) {
        if(view==null)return;
        view.showProgress();
        handler.execute(useCaseLogin, new UseCaseLogin.RequestValues(usuario, pass), new UseCase.UseCaseCallback<UseCaseLogin.ResponseValue>() {
            @Override
            public void onSuccess(UseCaseLogin.ResponseValue response) {
                view.hideProgress();
                if(response.getUsuarioUi()!=null){
                    startActivity(response.getUsuarioUi());
                  //  Log.d(TAG, "User "+ response.getUsuarioUi().getUsuario());
                }else Log.d(TAG, "User Invalido ");
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error al validar cuenta");
            }
        });

    }

    @Override
    public void crearCuenta() {
        Log.d(TAG, "crearCuenta");
        if(view==null)return;
        view.showProgress();
        String random= getRamdonUserAndPass();
        handler.execute(useCaseCrearCuenta, new UseCaseCrearCuenta.RequestValues(random, random, 0), new UseCase.UseCaseCallback<UseCaseCrearCuenta.ResponseValue>() {
            @Override
            public void onSuccess(UseCaseCrearCuenta.ResponseValue response) {
                view.hideProgress();
                if(response.getUsuarioUi()!=null){
                    Log.d(TAG, "response "+ response.getUsuarioUi().getUsuario());
                    startActivity(response.getUsuarioUi());

                }else
                    Log.d(TAG, "users exists! ");

            }
            @Override
            public void onError() {
                Log.d(TAG, "Error al crear cuenta ");
            }
        });

    }

    public  void startActivity(UsuarioUi usuarioUi){
        UsuarioWrapper usuarioWrapper= new UsuarioWrapper();
        usuarioWrapper.setUsuario(usuarioUi.getUsuario());
        usuarioWrapper.setPass(usuarioUi.getPass());
        usuarioWrapper.setIdcurrent(usuarioUi.getCurrentuserid());
        usuarioWrapper.setId(usuarioUi.getId());
        usuarioWrapper.setTipo(usuarioUi.getTipo());
        view.startMain(usuarioWrapper);
    }

    private String getRamdonUserAndPass() {

        String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };
        String ramdonLetras="";
        for(int i=0;i<3;i++){
            int numRandon = (int) Math.round(Math.random() * 26 ) ;
            ramdonLetras=ramdonLetras+""+(String.valueOf(abecedario[numRandon]));
        }

        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = aleatorio.nextInt(1000);
        Log.d(TAG, "getRamdonUserAndPass "+ intAletorio+""+ramdonLetras);
        return  intAletorio+""+ramdonLetras;
    }
}
