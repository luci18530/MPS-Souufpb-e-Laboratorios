package business.control;

import infra.InfraException;
import infra.UserFile;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import util.UserValidador;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.User;


public class UserManager {
	
	private Map<String, User> users;
	private UserFile userFile;
	
	public UserManager() throws InfraException {		
		
		userFile = new UserFile();
		users = userFile.loadUsers();
		 

	}
	
	public void addUser(String [] args) throws LoginInvalidException, EmailInvalidException, PasswordInvalidException  {
		
		UserValidador.validateName(args[0]);
		UserValidador.validateEmail(args[1]);
		UserValidador.validatePassword(args[2]);
		
		users.put(args[0], new User(args[0], args[1], args[2]));
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
	

}
