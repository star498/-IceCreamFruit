package com.example.estrellabarrientosmogollon.icecreamfruit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.estrellabarrientosmogollon.icecreamfruit.login.view.Login;
import com.example.estrellabarrientosmogollon.icecreamfruit.main.Main;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= new Intent(this, Main.class);
        startActivity(intent);
        finish();

    }
}
