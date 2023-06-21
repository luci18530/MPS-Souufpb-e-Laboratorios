package view;

import infra.Connection;
import infra.InfraException;
import infra.ProxyConnection;
import util.*;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.*;


public class TelaUsuario {
    private static UserManager userManager;
    private static TelaUsuario instance = null;
    private static CursoManager cursoManager;
    private static Connection connection;
    private static VocationalTest vocationalTest;
    

    private static final String MENU_LOGIN_OPTIONS = "Olá usuário, bem vindo ao SouUFPB\nEscolha a opcao desejada:\n1-Se cadastrar\n2-Logar\n3-Sair";
    private static final String MENU_APP_OPTIONS = "Escolha a opcao desejada:\n1-Ver os Cursos da UFPB\n2-Fazer teste vocacional\n3-Sair";

    private TelaUsuario() throws InfraException, IOException{
        connection = new ProxyConnection();
    }
    

    public static void main (String[] args) throws LoginInvalidException, InfraException, IOException {
        showMenuLogin();
    }

    public static void showMenuLogin() throws LoginInvalidException, InfraException, IOException {
        getInstance();
        String option = getUserInput(MENU_LOGIN_OPTIONS);
        if (option == null) return;
        readUserInputLogin(option);
    }

    public void showMenuApp(String email) throws InfraException, IOException {
        String option = getUserInput(MENU_APP_OPTIONS);
        if (option == null) return;
        TelaUsuario.getInstance().readUserInputApp(option, email);
    }

    private void readUserInputApp(String option, String email) throws InfraException, IOException {
        // Lê a opção do usuário no segundo menu
        //int choice = Integer.parseInt(option);
        switch (option) {
            case "1":
                // Implementar visualização dos cursos da UFPB
                cursoManager.showCursos();
                showMenuApp(email);
                break;
            case "2":
                // Conecta com o teste vocacional
                vocationalTest = new VocationalTest();
                vocationalTest.doVocationalTest(email);
                showMenuApp(email);
                break;
            case "3":
                // Implementar a saída
                System.exit(0);
                break;
            default:
                // Implementar opção inválida
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenuApp(email);
                break;
        }
    }


    public static void readUserInputLogin(String option) throws LoginInvalidException, InfraException, IOException {
        try {
            
            userManager = new UserManager();
            cursoManager = new CursoManager();

        } catch (InfraException e) {
            String option2 = JOptionPane.showInputDialog(e.getMessage());
        }
        //int choice = Integer.parseInt(option);
        switch (option) {

            case "1":
                // Realiza cadastro de usuário
                //registerUser();
                userManager.add();
                showMenuLogin();
                break;

            case "2": 
                // Realiza login de usuário
                loginUser();
                showMenuLogin();
                break;

            case "3": 
                // Encerra a aplicação
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenuLogin();
                break;
        }
    }


    private static void loginUser() throws LoginInvalidException, InfraException, IOException{
        while (true) {
            String email = getUserInput("Email do usuario:");
            if (email == null) break;
            String password = getUserInput("Senha do usuario:");
            if (password == null) break;
    
            if (connection.validateConnection(email, password)) {
                // userManager.updateFile();
                // showMessage("Login bem-sucedido!");
                break;
            } else {
                showMessage("Email ou senha incorretos. Por favor, tente novamente.");
            }
        }
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private static String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message, "Digite aqui");
    }

    public static TelaUsuario getInstance() throws InfraException, IOException{

        if(instance == null){
            instance = new TelaUsuario();
        }
        
        return instance;
    }


}
