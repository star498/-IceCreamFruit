package com.example.estrellabarrientosmogollon.icecreamfruit.login.entities;

import android.os.Bundle;

import java.io.Serializable;

public class UsuarioWrapper implements Serializable {
    public final static String  USUARIO = "UsuarioWrapper.usuario";
    public final static String  PASS = "UsuarioWrapper.pass";
    public final static String  IDCURRENT = "UsuarioWrapper.idcurrent";
    public final static String  ID = "UsuarioWrapper.id";
    public final static String  TIPO = "UsuarioWrapper.tipo";

    private String usuario;
    private String pass;
    private String idcurrent;
    private String id;
    private int tipo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdcurrent() {
        return idcurrent;
    }

    public void setIdcurrent(String idcurrent) {
        this.idcurrent = idcurrent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public Bundle convertBundle(Bundle bundle){
        bundle.putString(USUARIO, usuario);
        bundle.putString(PASS, pass);
        bundle.putString(IDCURRENT, idcurrent);
        bundle.putString(ID, id);
        bundle.putInt(TIPO, tipo);
        return bundle;
    }
    public static UsuarioWrapper getBundle(Bundle bundle){
        UsuarioWrapper usuarioWrapper= new UsuarioWrapper();
        usuarioWrapper.setId(bundle.getString(ID));
        usuarioWrapper.setUsuario(bundle.getString(USUARIO));
        usuarioWrapper.setPass(bundle.getString(PASS));
        usuarioWrapper.setIdcurrent(bundle.getString(IDCURRENT));
        usuarioWrapper.setTipo(bundle.getInt(TIPO));
        return usuarioWrapper;
    }
}
