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
        boolean flag = false;
    
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {

            AdminMenu adminMenu = AdminMenu.getInstance();
            adminMenu.showMenu();
            return true;
        }
        else{
            flag = userConnection.validateConnection(email, password);
        }
        return flag;
    }
    
}
