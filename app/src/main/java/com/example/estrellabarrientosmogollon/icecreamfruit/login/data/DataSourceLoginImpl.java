package com.example.estrellabarrientosmogollon.icecreamfruit.login.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.FirebaseHelper;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.LastOwnerException;

public class DataSourceLoginImpl implements  DataSourceLogin {

    private String TAG=DataSourceLoginImpl.class.getSimpleName();
    final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    @Override
    public void createAccount(final String usuario, final  String pass, final int tipo , final String celular,final Callback<UsuarioUi>callback) {

        final DatabaseReference databaseUser= FirebaseDatabase.getInstance().getReference().child("usuario");

        final String correo = usuario+"@icecreamfruit.com";

        Log.d(TAG, "createAccountOrLogin" );
        databaseUser.orderByChild("usuario").equalTo(correo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d(TAG, "existe" );
                    callback.onLoad(false, null);
                }
                else{
                    Log.d(TAG, "no existe" );
                    firebaseAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "crear Usuario con Correo" + task.isSuccessful());
                            if (!task.isSuccessful()) {
                                Log.d(TAG, "Creacion fallida");
                            } else {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String id= databaseUser.push().getKey();
                                UsuarioUi usuarioUi = new UsuarioUi();
                                usuarioUi.setId(id);
                                usuarioUi.setEstado(true);
                                usuarioUi.setPass(pass);
                                usuarioUi.setUsuario(correo);
                                usuarioUi.setTipo(tipo);
                                usuarioUi.setCelular(celular);
                                usuarioUi.setCurrentuserid(user.getUid());
                                databaseUser.child(id).setValue(usuarioUi);
                                callback.onLoad(true, usuarioUi);
                            }
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled al validar usuario");
                callback.onLoad(false, null);
            }
        });
    }

    @Override
    public void login(String usuario, String pass, final Callback<UsuarioUi> callback) {
        final DatabaseReference databaseUser= FirebaseDatabase.getInstance().getReference().child("usuario");
        final String correo = usuario+"@icecreamfruit.com";
        Log.d(TAG, "correo "+ correo);
        firebaseAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, " sing in");
                    databaseUser.orderByChild("currentuserid").equalTo(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                UsuarioUi usuarioUi= postSnapshot.getValue(UsuarioUi.class);
                                callback.onLoad(true, usuarioUi);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else callback.onLoad(false, null);
            }
        });
    }
}
