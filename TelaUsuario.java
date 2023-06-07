package view;

import infra.InfraException;
import util.*;

import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import business.control.*;
import business.model.Curso;
import business.model.Questionario;
import business.model.Resultado;
import testes.Testes;
import factory.CursoFactory;
import factory.CursoFactoryImpl;
import factory.QuestionarioFactory;
import factory.QuestionarioFactoryImpl;
import factory.UserFactory;
import factory.UserFactoryImpl;

public class TelaUsuario {
    private UserManager userManager;
    private static TelaUsuario instance = null;
    private LoginValidator loginValidator;
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    

    private static final String MENU_LOGIN_OPTIONS = "Olá usuário, bem vindo ao SouUFPB\nEscolha a opcao desejada:\n1-Se cadastrar\n2-Logar\n3-Sair";
    private static final String MENU_APP_OPTIONS = "Escolha a opcao desejada:\n1-Ver os Cursos da UFPB\n2-Fazer teste vocacional\n3-Sair";
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin012";

    private TelaUsuario() throws InfraException{
        this.loginValidator = new LoginValidator();
    }
    

    public static void main (String[] args) throws LoginInvalidException, InfraException {
        showMenuLogin();
    }

    public static void showMenuLogin() throws LoginInvalidException, InfraException {
        TelaUsuario main = getInstance();
        String option = getUserInput(MENU_LOGIN_OPTIONS);
        main.readUserInputLogin(option);
    }

    public static void showMenuApp(String email) throws InfraException {
        String option = getUserInput(MENU_APP_OPTIONS);
        TelaUsuario.getInstance().readUserInputApp(option, email);
    }

    private void readUserInputApp(String option, String email) throws InfraException {
        // Lê a opção do usuário no segundo menu
        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                // Implementar visualização dos cursos da UFPB
                showCursos();
                break;
            case 2:
                // Conecta com o teste vocacional
                doVocationalTest(email);
                break;
            case 3:
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


    public void readUserInputLogin(String option) throws LoginInvalidException, InfraException {
        try {
            UserFactory userFactory = new UserFactoryImpl();
            userManager = new UserManager(userFactory);
            CursoFactory cursoFactory = new CursoFactoryImpl();
            cursoManager = new CursoManager(cursoFactory);
            QuestionarioFactory questionarioFactory = new QuestionarioFactoryImpl();
            questionarioManager = new QuestionarioManager(questionarioFactory);
        } catch (InfraException e) {
            String option2 = JOptionPane.showInputDialog(e.getMessage());
        }
        int choice = Integer.parseInt(option);
        switch (choice) {

            case 1:
                // Realiza cadastro de usuário
                registerUser();
                showMenuLogin();
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
                showMenuLogin();
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

    private void loginUser() throws LoginInvalidException, InfraException{
        while (true) {
            String email = getUserInput("Email do usuario:");
            String password = getUserInput("Senha do usuario:");
    
            if (loginValidator.checkUserLogin(email,password)) {
                showMessage("Login bem-sucedido!");
                if (isAdmin(email, password)) {
                    try {
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.showMenu();
                    } catch (InfraException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else {
                    showMenuApp(email);
                }
                break;
            } else {
                showMessage("Email ou senha incorretos. Por favor, tente novamente.");
            }
        }
    }
    

    private boolean isAdmin(String email, String password) {
        return ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password);
    }

    private static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private static String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message, "Digite aqui");
    }

    public static TelaUsuario getInstance() throws InfraException{

        if(instance == null){
            instance = new TelaUsuario();
        }
        
        return instance;
    }


    private void showCursos() throws InfraException {
        try {
            Map<String, Curso> cursos = cursoManager.getCursos();
            StringBuilder listaDeCursos = new StringBuilder();
            for (Curso curso : cursos.values()) {
                listaDeCursos.append("[ Nome: ").append(curso.getNome()).append(" | Cidade: ").append(curso.getCidade()).append(" | Centro: ").append(curso.getCentro()).append("| Área: ").append(curso.getArea()).append(" ]\n");
            }
            JOptionPane.showMessageDialog(null, listaDeCursos);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public void doVocationalTest(String email) throws InfraException {
        // Recupera o questionário e o resultado do usuário
        Map<String, Questionario> questionarios = questionarioManager.getQuestionarios();
        Resultado resultado = userManager.getUserResult(email);
        if(resultado == null) {
            resultado = new Resultado();
        }

        // Executa o teste
        for (Questionario questionario : questionarios.values()) {
            System.out.println(questionario.getPergunta());
            System.out.println("Responda de 1 (discordo totalmente) a 5 (concordo totalmente):");

            // Lê a resposta do usuário
            int resposta = Integer.parseInt(scan("Sua resposta: "));
            if (resposta > 5 || resposta < 0){
                // resolver isso
            } 
            // Adiciona a resposta ao resultado
            resultado.addScore(questionario.getArea(), resposta);
        }

        // Salva o resultado do teste
        userManager.setUserResult(email, resultado);

        // Exibe o resultado do teste
        System.out.println("Resultado do teste:");
        for (Map.Entry<String, Integer> entry : resultado.getScorePorArea().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private static String scan(String message) {
        // Uma função simplificada para coletar entrada do usuário
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

}
