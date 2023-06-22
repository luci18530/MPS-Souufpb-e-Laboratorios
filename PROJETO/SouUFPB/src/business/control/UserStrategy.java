package business.control;

import infra.SaveCommandInvoker;
import infra.InfraException;
import infra.LoadCommandInvoker;
import infra.LoadUsers;
import infra.SaveUser;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import util.UserValidador;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import infra.ConcreteMemento;
import infra.Memento;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import business.model.Resultado;
import business.model.User;
import factory.UserFactory;
import factory.UserFactoryImpl;


public class UserStrategy implements Strategy<User> {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static Map<String, User> users = new HashMap<String,User>();
	private SaveUser saveFile;
	private LoadUsers loadFile;
	private Map<String, Resultado> resultados;
	private static SaveCommandInvoker<User> saveCommandInvoker = new SaveCommandInvoker<>();
	private static LoadCommandInvoker<User> loadCommandInvoker = new LoadCommandInvoker<>();
    private UserFactory userFactory = new UserFactoryImpl();

	public UserStrategy() throws InfraException, IOException {

		try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("UserManagerLog.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }

		saveFile = new SaveUser();
		loadFile = new LoadUsers();
		loadCommandInvoker.setCommand(loadFile);
		saveCommandInvoker.setCommand(saveFile);
		resultados = new HashMap<>();
		loadFile();
	}

	public void add() throws InfraException  {

		while (true) {
            String name = JOptionPane.showInputDialog("Nome do usuario:");
            if (name == null) {
                break;
            }

            String email = JOptionPane.showInputDialog("Email do usuario:");
            if (email == null) {
                break;
            }

            String pass = JOptionPane.showInputDialog("Senha do usuario:");
            if (pass == null) {
                break;
            }

			try {
                String[] args = {name, email, pass};
				validate(args[0], args[1], args[2]);
				User user = userFactory.createUser(args[0], args[1], args[2]);
				users.put(args[0], user);
				saveCommandInvoker.invoke(users);
				JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
				break;
			} catch (LoginInvalidException | EmailInvalidException | PasswordInvalidException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro desconhecido: " + e.getMessage());
            }
		}	
	}

	public static void validate(String nome, String email, String senha) throws LoginInvalidException, EmailInvalidException, PasswordInvalidException{
		UserValidador.validateName(nome);
		UserValidador.validateEmail(email);
		UserValidador.validatePassword(senha);
	}		

	public void remove() throws InfraException {

		while (true) {
			
			String name = JOptionPane.showInputDialog("Digite o nome do usuário a deletar");

			if (!users.containsKey(name)) {
				throw new IllegalArgumentException("Usuario nao encontrado!");
			}
			else{
				users.remove(name);
				saveCommandInvoker.invoke(users);
				break;
			}
		}
	}

	public static void updateFile() throws InfraException {
		saveCommandInvoker.invoke(users);
	}

	public static void loadFile() throws InfraException{
		loadCommandInvoker.invoke();
	}
	
	public Map<String, User> list() throws InfraException {


		try {
			Map<String, User> mylist = loadCommandInvoker.invoke();
			return mylist;

		} catch (NullPointerException ex){
	        logger.severe(ex.getMessage());
	        throw new InfraException("[USER ALL CLIENTS]Erro de persistencia, contacte o admin ou tente mais tarde");
	           
	    }
	}

    public Resultado getUserResult(String email) {
        return resultados.get(email);
    }

    public void setUserResult(String email, Resultado resultado) {
        resultados.put(email, resultado);
    }
	
	public Memento save() throws InfraException{
		return new ConcreteMemento<>(users, this);
	}

	public void restore(Memento memento) throws InfraException{
		users = (Map<String, User>) memento.getState();
		updateFile();
	}

}
