package util;

import java.util.Iterator;
import business.model.User;
import business.control.UserManager;
import infra.InfraException;


public class LoginValidator {

    private UserManager userManager;

    public LoginValidator() throws InfraException{
        this.userManager = new UserManager();
    }
    
    public boolean checkUserLogin(String email, String password) throws LoginInvalidException {
        // Verifica se o login do usuário é válido
            Iterator<User> users;
            try {
                users = userManager.getAllClients().values().iterator();

                while (users.hasNext()) {
                    User user = users.next();
                    // Usando equalsIgnoreCase para comparação de email, uma vez que os emails não são sensíveis a maiúsculas e minúsculas
                    if (user.getEmail().equalsIgnoreCase(email) && user.getSenha().equals(password)) {
                        return true;
                    }
            } 
        }catch (InfraException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    

        return false;
    }
}
