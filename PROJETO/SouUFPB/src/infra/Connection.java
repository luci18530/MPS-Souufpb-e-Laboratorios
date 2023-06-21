package infra;

import java.io.IOException;

import util.LoginInvalidException;

public interface Connection {
    public boolean validateConnection(String email, String password) throws LoginInvalidException, InfraException, IOException;
}
