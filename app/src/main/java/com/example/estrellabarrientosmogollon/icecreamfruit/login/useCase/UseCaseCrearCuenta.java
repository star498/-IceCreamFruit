package com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCase;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.DataSourceLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.RepositoryLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;

public class UseCaseCrearCuenta extends UseCase<UseCaseCrearCuenta.RequestValues, UseCaseCrearCuenta.ResponseValue> {

    private RepositoryLogin repositoryLogin;

    public UseCaseCrearCuenta(RepositoryLogin repositoryLogin) {
        this.repositoryLogin = repositoryLogin;
    }


    @Override
    protected void executeUseCase(RequestValues request) {
        repositoryLogin.createAccount(request.getUser(), request.getPass(), request.getTipo(), request.getCelular(),new DataSourceLogin.Callback<UsuarioUi>() {
            @Override
            public void onLoad(boolean success, UsuarioUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private String user;
        private String pass;
        private int tipo;
        private String celular;


        public RequestValues(String user, String pass, int tipo, String celular) {
            this.user = user;
            this.pass = pass;
            this.tipo = tipo;
            this.celular=celular;
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return pass;
        }

        public int getTipo() {
            return tipo;
        }

        public String getCelular() {
            return celular;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue {
     private UsuarioUi usuarioUi;

        public ResponseValue(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }
}
