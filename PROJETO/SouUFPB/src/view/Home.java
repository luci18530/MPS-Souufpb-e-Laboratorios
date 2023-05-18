package view;

import util.LoginInvalidException;

public class Home {

    public static void main(String[] args) throws LoginInvalidException {
        TelaUsuario telaUsuario = TelaUsuario.getInstance();
        telaUsuario.showMenuLogin();
    }
}
