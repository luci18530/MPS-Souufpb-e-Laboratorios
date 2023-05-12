package view;

import infra.InfraException;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import business.control.UserManager;
import business.model.User;
import testes.Testes;
import cursos.Cursos;

public class Telausuario {
    UserManager userManager;

    public static void main (String[] args) {
        showMenuUsuario();
    }

    public static void showMenuUsuario() {
        // Mostra o menu inicial para o usuário
        String option = JOptionPane.showInputDialog("Olá usuário, bem vindo ao SouUFPB\nEscolha a opcao desejada:\n1-Se cadastrar\n2-Logar\n3-Sair","Sua opcao");     

        Telausuario main = new Telausuario();

        main.readUserInput(option);
    }

    public static void showMenuUsuario2(String email) {
        // Mostra o segundo menu para o usuário
        String option = JOptionPane.showInputDialog("Escolha a opcao desejada:\n1-Ver os Cursos da UFPB\n2-Fazer teste vocacional\n3-Sair","Sua opcao");

        Telausuario secondmain = new Telausuario();

        secondmain.secondreadUserInput(option, email);
    }

    private void secondreadUserInput(String option, String email) {
        // Lê a opção do usuário no segundo menu
        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                // Implementar visualização dos cursos da UFPB
                Cursos.entraremcursos();
                break;
            case 2:
                // Conecta com o teste vocacional
                Testes.questionario(email);
                break;
            case 3:
                // Implementar a saída
                System.exit(0);
                break;
            default:
                // Implementar opção inválida
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenuUsuario2(email);
                break;
        }
    }

    public void readUserInput(String option) {
        try {
            userManager = new UserManager();
        } catch (InfraException e) {
            String option2 = JOptionPane.showInputDialog(e.getMessage());
        }
        int choice = Integer.parseInt(option);
        switch (choice) {

            case 1:
                // Realiza cadastro de usuário
                registerUser();
                showMenuUsuario();
                break;

            case 2: 
                // Realiza login de usuário
                loginUser();
                break;

            case 3: 
                // Encerra a aplicação
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenuUsuario();
                break;
        }
    }

    private void registerUser() {
        // Implementa o registro do usuário
        // Continua pedindo as informações até que todas estejam corretas
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
                this.userManager.addUser(args);
                JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
                break;
            } catch (LoginInvalidException | EmailInvalidException | PasswordInvalidException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro desconhecido: " + e.getMessage());
            }
        }
    }

    private void loginUser() {
        // Implementa o login do usuário
        boolean loggedIn = false;

        while (!loggedIn) {
            String email = JOptionPane.showInputDialog("Email do usuario:");
            if (email == null) {
                showMenuUsuario();
                return;
            }

            String password = JOptionPane.showInputDialog("Senha do usuario:");
            if (password == null) {
                showMenuUsuario();
                return;
            }

            loggedIn = checkUserLogin(email, password);

            if (loggedIn) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                showMenuUsuario2(email);
            } else {
                JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Por favor, tente novamente.");
            }
        }
    }

    private boolean checkUserLogin(String email, String password) {
        // Verifica se o login do usuário é válido
        try {
            Iterator<User> users = userManager.getAllClients().values().iterator();
            while (users.hasNext()) {
                User user = users.next();
                // Usando equalsIgnoreCase para comparação de email, uma vez que os emails não são sensíveis a maiúsculas e minúsculas
                if (user.getEmail().equalsIgnoreCase(email) && user.getSenha().equals(password)) {
                    return true;
                }
            }
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar login: " + e.getMessage());
        }
        return false;
    }
}
