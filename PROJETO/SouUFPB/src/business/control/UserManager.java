package business.control;

import infra.InfraException;
import infra.UserFile;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import util.UserValidador;
import java.util.Map;
import java.util.logging.Logger;
import java.util.HashMap;
import business.model.Resultado;
import business.model.User;
import factory.UserFactory;


public class UserManager {
	
	private Map<String, User> users;
	private UserFile userFile;
    private UserFactory userFactory;
	private Map<String, Resultado> resultados;

	public UserManager(UserFactory userFactory) throws InfraException {
        this.userFactory = userFactory;
		userFile = new UserFile();
		users = userFile.loadUsers();
		resultados = new HashMap<>();
	}

	public void addUser(String [] args) throws LoginInvalidException, EmailInvalidException, PasswordInvalidException  {
		UserValidador.validateName(args[0]);
		UserValidador.validateEmail(args[1]);
		UserValidador.validatePassword(args[2]);
		
		User user = userFactory.createUser(args[0], args[1], args[2]);
		users.put(args[0], user);
		userFile.saveUsers(users);
	}

	public void removeUser(String name) throws InfraException {
		if (!users.containsKey(name)) {
			throw new IllegalArgumentException("Usuario nao encontrado!");
		}
	
		users.remove(name);
		userFile.saveUsers(users);
	}
	
	public Map<String, User> getAllClients() throws InfraException {

		Logger logger = userFile.getLogger();

		try {
			Map<String, User> mylist = userFile.loadUsers();
			return mylist;

		} catch (NullPointerException ex){
	        logger.severe(ex.getMessage());
	        throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
	           
	    }
	}

    public Resultado getUserResult(String email) {
        return resultados.get(email);
    }

    public void setUserResult(String email, Resultado resultado) {
        resultados.put(email, resultado);
    }
}
