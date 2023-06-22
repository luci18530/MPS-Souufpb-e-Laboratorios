package util;

import java.io.IOException;
import java.util.Iterator;
import business.model.User;
import business.control.UserStrategy;
import infra.InfraException;


public class LoginValidator {

    private UserStrategy userManager;

    public LoginValidator() throws InfraException, IOException{
        userManager = new UserStrategy();
    }
    
    public boolean checkUserLogin(String email, String password) throws LoginInvalidException {
        // Verifica se o login do usuário é válido
            Iterator<User> users;
            try {
                users = userManager.list().values().iterator();

                while (users.hasNext()) {
                    User user = users.next();
                    // Usando equalsIgnoreCase para comparação de email, uma vez que os emails não são sensíveis a maiúsculas e minúsculas
                    if (user.getEmail().equalsIgnoreCase(email) && user.getSenha().equals(password)) {
                        user.updateTotalAccessCount();
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
