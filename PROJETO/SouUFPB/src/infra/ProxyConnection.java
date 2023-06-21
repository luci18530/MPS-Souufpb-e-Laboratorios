package infra;

import java.io.IOException;

import util.LoginInvalidException;
import view.AdminMenu;

public class ProxyConnection implements Connection {

    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin012";
    private UserConnection userConnection;

    public ProxyConnection() throws InfraException, IOException{
        this.userConnection = new UserConnection();

    }

    @Override
    public boolean validateConnection(String email, String password) throws LoginInvalidException, InfraException, IOException {
        // TODO Auto-generated method stub
        
    
        if (email == ADMIN_EMAIL && password == ADMIN_PASSWORD) {
            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.showMenu();
        }
        else{
            userConnection.validateConnection(email, password);
        }
        return false;
    }
    
}
