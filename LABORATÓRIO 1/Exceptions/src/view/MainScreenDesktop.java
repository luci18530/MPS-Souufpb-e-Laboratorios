package view;

import infra.InfraException;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;

import java.util.Iterator;

import javax.swing.JOptionPane;

import business.control.UserManager;
import business.model.User;

public class MainScreenDesktop {
	
	UserManager userManager;
	
	public static void main (String[] args) {
		showMenu();
	}
	
	public static void showMenu() {
		String option = JOptionPane.showInputDialog("Bem vindo ao sistema de SASF!\nEscolha a opcao desejada:\n1-Listar Usuarios\n2-Sair","Sua opcao");		
		
		MainScreenDesktop main = new MainScreenDesktop();
		
		main.readUserInput(option);
	}
	
	public void readUserInput(String option) {
		try {
			userManager = new UserManager();
		} catch (InfraException e) {
			String option2 = JOptionPane.showInputDialog(e.getMessage());
		}
		int choice = Integer.parseInt(option);
		boolean checkedLogin = false;
		boolean checkedemail = false;
		boolean checkedPassword = false;
		switch (choice) {
	
			case 1:
				String usuarios = "";
				Iterator<User> users;
				try {
					users = this.userManager.getAllClients().values().iterator();
					while (users.hasNext()) {
						User user = users.next();
						usuarios = usuarios + "[ Login: " + user.getLogin() + " | Email: " + user.getEmail() + " | Senha: " + user.getSenha() + " ]" + "\n";
					}
					JOptionPane.showMessageDialog(null, usuarios);
				} catch (InfraException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
	
				showMenu();
				break;

			case 2:
			// exit
				System.exit(0);
				break;
	
		}
	}
}