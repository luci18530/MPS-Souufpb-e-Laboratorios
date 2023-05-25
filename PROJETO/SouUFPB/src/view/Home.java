package view;

import infra.InfraException;
import util.LoginInvalidException;

public class Home {

    public static void main(String[] args) throws LoginInvalidException, InfraException {
        TelaUsuario telaUsuario = TelaUsuario.getInstance();
        telaUsuario.showMenuLogin();
    }
}
