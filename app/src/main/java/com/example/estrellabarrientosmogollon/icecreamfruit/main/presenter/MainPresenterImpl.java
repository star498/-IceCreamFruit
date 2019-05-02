package com.example.estrellabarrientosmogollon.icecreamfruit.main.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCaseHandler;
import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.activity.BasePresenterImpl;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioWrapper;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.view.MainView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {


    String TAG=MainPresenterImpl.class.getSimpleName();

    public MainPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private UsuarioWrapper usuarioWrapper;
    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        validarConnectUser();

    }

    private void validarConnectUser() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    showUser();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    signInFirebase();
                }
                // ...
            }
        };
    }

    private void showUser() {
       if(usuarioWrapper!=null){

       }

    }

    private void signInFirebase() {
        if(view!=null)view.startLogin();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.usuarioWrapper= UsuarioWrapper.getBundle(extras);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void logout() {
      if(view==null)return;
        mAuth.signOut();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
       view.startLogin();

    }
}
