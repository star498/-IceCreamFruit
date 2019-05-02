package com.example.estrellabarrientosmogollon.icecreamfruit.login.useCase;

import com.example.estrellabarrientosmogollon.icecreamfruit.config.base.UseCase;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.DataSourceLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.data.RepositoryLogin;
import com.example.estrellabarrientosmogollon.icecreamfruit.login.entities.UsuarioUi;

public class UseCaseLogin extends UseCase<UseCaseLogin.RequestValues, UseCaseLogin.ResponseValue> {

    RepositoryLogin repositoryLogin;

    public UseCaseLogin(RepositoryLogin repositoryLogin) {
        this.repositoryLogin = repositoryLogin;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repositoryLogin.login(requestValues.getUsuario(), requestValues.getPass(), new DataSourceLogin.Callback<UsuarioUi>() {
            @Override
            public void onLoad(boolean success, UsuarioUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
      private String usuario;
      private String pass;

        public RequestValues(String usuario, String pass) {
            this.usuario = usuario;
            this.pass = pass;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getPass() {
            return pass;
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
