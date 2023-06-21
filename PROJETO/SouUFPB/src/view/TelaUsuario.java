package view;

import infra.InfraException;
import util.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import business.control.*;
import business.model.*;


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

    private TelaUsuario() throws InfraException, IOException{
        this.loginValidator = new LoginValidator();
    }
    

    public static void main (String[] args) throws LoginInvalidException, InfraException, IOException {
        showMenuLogin();
    }

    public static void showMenuLogin() throws LoginInvalidException, InfraException, IOException {
        TelaUsuario main = getInstance();
        String option = getUserInput(MENU_LOGIN_OPTIONS);
        if (option == null) return;
        main.readUserInputLogin(option);
    }

    public static void showMenuApp(String email) throws InfraException, IOException {
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
                doVocationalTest(email);
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


    public void readUserInputLogin(String option) throws LoginInvalidException, InfraException, IOException {
        try {
            
            userManager = new UserManager();
            cursoManager = new CursoManager();
            questionarioManager = new QuestionarioManager();

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


    private void loginUser() throws LoginInvalidException, InfraException, IOException{
        while (true) {
            String email = getUserInput("Email do usuario:");
            if (email == null) break;
            String password = getUserInput("Senha do usuario:");
            if (password == null) break;
    
            if (loginValidator.checkUserLogin(email,password)) {
                userManager.updateFile();
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

    public static TelaUsuario getInstance() throws InfraException, IOException{

        if(instance == null){
            instance = new TelaUsuario();
        }
        
        return instance;
    }


    public void doVocationalTest(String email) throws InfraException {
        // Recupera o questionário e o resultado do usuário
        Map<String, Questionario> questionarios = questionarioManager.list();
        Resultado resultado = userManager.getUserResult(email);
        if(resultado == null) {
            resultado = new Resultado();
        }

        // Executa o teste
        for (Questionario questionario : questionarios.values()) {
            String pergunta = questionario.getPergunta();
            String area = questionario.getArea();

            // Lê a resposta do usuário
            Object[] options = {"1", "2", "3", "4", "5"};
            int resposta = JOptionPane.showOptionDialog(null,
                    pergunta, 
                    "Teste Vocacional",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (resposta >= 0 && resposta <= 4) { // Verifica se uma resposta válida foi selecionada
                resultado.addScore(area, resposta+1);
                
            }

        }

        userManager.setUserResult(email, resultado);
        JOptionPane.showMessageDialog(null, resultadoToString(resultado));

        // Exibe o resultado do teste
        System.out.println("Resultado do teste:");
        for (Map.Entry<String, Integer> entry : resultado.getScorePorArea().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        resultado = userManager.getUserResult(email);
        String areaComMaiorPontuacao = resultado.getAreaComMaiorPontuacao();
        List<Curso> cursosRecomendados = cursoManager.getCursosPorArea(areaComMaiorPontuacao);
    
        StringBuilder listaDeCursosRecomendados = new StringBuilder();
        for (Curso curso : cursosRecomendados) {
            listaDeCursosRecomendados.append("[ Nome: ").append(curso.getNome()).append(" | Cidade: ").append(curso.getCidade()).append(" | Centro: ").append(curso.getCentro()).append(" ]\n");
        }
        JOptionPane.showMessageDialog(null, "Cursos recomendados para a área " + areaComMaiorPontuacao + ":\n" + listaDeCursosRecomendados);
    }
    
    private String resultadoToString(Resultado resultado) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : resultado.getScorePorArea().entrySet()) {
            sb.append("Area: ").append(entry.getKey())
              .append(", Pontuação: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

}
