package view;


import infra.InfraException;
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
		String option = JOptionPane.showInputDialog("Bem vindo ao sistema de SASF!\nEscolha a opcao desejada:\n1-Cadastrar Usuario\n2-Listar Usuarios\n3-Excluir Usuario\n4-Sair","Sua opcao");		
		
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
		boolean checkedPassword = false;
		switch (choice) {
	
			case 1:
	
			while (true) {
				String name = "";
				String pass = "";

				if (!checkedLogin) {
					name = JOptionPane.showInputDialog("Nome do usuario:");
				}
			
				if (!checkedPassword) {
					pass = JOptionPane.showInputDialog("Senha do usuario:");
				}
				
			
				try {
					String[] args = {name, pass};
					this.userManager.addUser(args);
					JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
					break;
				} catch (LoginInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				} catch (PasswordInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro desconhecido: " + e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				}
			}
				showMenu();
				break;
	
			case 2:
				String usuarios = "";
				Iterator<User> users;
				try {
					users = this.userManager.getAllClients().values().iterator();
					while (users.hasNext()) {
						User user = users.next();
						usuarios = usuarios + "[ Login: " + user.getLogin() + " || Senha: " + user.getSenha() + " ]" + "\n";
					}
					JOptionPane.showMessageDialog(null, usuarios);
				} catch (InfraException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
	
				showMenu();
				break;
				case 3:
				while (true) {
					String name = JOptionPane.showInputDialog("Digite o nome do usuario a ser removido (ou 0 para voltar ao menu):");
					if (name.equals("0")) {
						break;
					}
			
					try {
						this.userManager.removeUser(name);
						JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!");
						break;
					} catch (InfraException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				showMenu();
				break;
	
		}
	}
}