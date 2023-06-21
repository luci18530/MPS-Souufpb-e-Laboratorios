package infra;

import java.io.IOException;

import util.LoginInvalidException;
import util.LoginValidator;
import view.TelaUsuario;

public class UserConnection implements Connection {

    private LoginValidator loginValidator;

    public UserConnection() throws InfraException, IOException{
        this.loginValidator = new LoginValidator();
    }
    
    public boolean validateConnection(String email, String password) throws LoginInvalidException, InfraException, IOException{
        if (loginValidator.checkUserLogin(email,password)) {

            TelaUsuario telaUsuario = TelaUsuario.getInstance();
            telaUsuario.showMenuApp(email);
            return true;
        }
        return false;
    }
}
