package com.example.estrellabarrientosmogollon.icecreamfruit.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private static final String TAG = FirebaseHelper.class.getSimpleName();

    private DatabaseReference databaseReference;

    public FirebaseHelper(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

}
