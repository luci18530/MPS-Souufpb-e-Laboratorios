package view;

import infra.InfraException;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import business.control.UserManager;
import business.model.User;

public class Telausuario {
    UserManager userManager;
    // using joptionpane to take user input
    public static void main (String[] args) {
        showMenuUsuario();
    }

    public static void showMenuUsuario() {
        String option = JOptionPane.showInputDialog("Olá usuário, bem vindo ao SouUFPB\nEscolha a opcao desejada:\n1-Se cadastrar\n2-Logar\n3-Sair","Sua opcao");     

        Telausuario main = new Telausuario();

        main.readUserInput(option);
    }

    // second menu
    public static void showMenuUsuario2() {
        String option = JOptionPane.showInputDialog("Escolha a opcao desejada:\n1-Ver os Cursos da UFPB\n2-Fazer teste vocacional\n3-Sair","Sua opcao");

        Telausuario secondmain = new Telausuario();

        secondmain.secondreadUserInput(option);
    }

    // using joptionpane to take user input and if the login email and password are in the database, progress to the second menu

    private void secondreadUserInput(String option) {
        // to do
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

            while (true) {
				String name = "";
				String email = "";
				String pass = "";

				if (!checkedLogin) {
					name = JOptionPane.showInputDialog("Nome do usuario:");
					// cancel button
					if (name == null) {
						break;
					}
					 
				}

				if (!checkedemail) {
					email = JOptionPane.showInputDialog("Email do usuario:");
					// cancel button
					if (email == null) {
						break;
					}
				}
			
				if (!checkedPassword) {
					pass = JOptionPane.showInputDialog("Senha do usuario:");
					// cancel button
					if (pass == null) {
						break;
					}
				}
				
			
				try {
					String[] args = {name, email, pass};
					this.userManager.addUser(args);
					JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
					break;
				} catch (LoginInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				} 
				
				catch (EmailInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				}	
				
				catch (PasswordInvalidException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro desconhecido: " + e.getMessage());
					checkedLogin = false;
					checkedPassword = false;
				}
			}
				showMenuUsuario();
				break;

            case 2: // logar
                String email;
                String password;
                boolean loggedIn = false;

                while (!loggedIn) {
                    email = JOptionPane.showInputDialog("Email do usuario:");
                    if (email == null) {
                        showMenuUsuario();
                        return;
                    }

                    password = JOptionPane.showInputDialog("Senha do usuario:");
                    if (password == null) {
                        showMenuUsuario();
                        return;
                    }

                    loggedIn = checkUserLogin(email, password);

                    if (loggedIn) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        showMenuUsuario2();
                    } else {
                        JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Por favor, tente novamente.");
                    }
                }

                break;

            case 3: // sair
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenuUsuario();
                break;
        }
    }

    private boolean checkUserLogin(String email, String password) {
        try {
            Iterator<User> users = userManager.getAllClients().values().iterator();
            while (users.hasNext()) {
                User user = users.next();
                if (user.getEmail().equals(email) && user.getSenha().equals(password)) {
                    return true;
                }
            }
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar login: " + e.getMessage());
        }
        return false;
    }
}
