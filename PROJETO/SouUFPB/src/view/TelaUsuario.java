package view;

import infra.InfraException;
import util.EmailInvalidException;
import util.LoginInvalidException;
import util.PasswordInvalidException;
import util.LoginValidator;
import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import business.control.UserManager;
import testes.Testes;
import cursos.Cursos;

public class TelaUsuario {
    private UserManager userManager;
    private static TelaUsuario instance = null;
    private LoginValidator loginValidator;
    private CursoManager cursoManager;
    private QuestionarioManager questionarioManager;

    private TelaUsuario() throws InfraException{
        this.loginValidator = new LoginValidator();
    }
    

    public static void main (String[] args) throws LoginInvalidException, InfraException {
        showMenuLogin();
    }

    public static void showMenuLogin() throws LoginInvalidException, InfraException {
        // Mostra o menu inicial para o usuário

        TelaUsuario main = getInstance();

        String option = JOptionPane.showInputDialog("Olá usuário, bem vindo ao SouUFPB\nEscolha a opcao desejada:\n1-Se cadastrar\n2-Logar\n3-Sair","Sua opcao");     

        main.readUserInputLogin(option);
    }

    public static void showMenuApp(String email) throws InfraException {
        // Mostra o segundo menu para o usuário
        String option = JOptionPane.showInputDialog("Escolha a opcao desejada:\n1-Ver os Cursos da UFPB\n2-Fazer teste vocacional\n3-Sair","Sua opcao");

        TelaUsuario secondmain = new TelaUsuario();

        secondmain.readUserInputApp(option, email);
    }

    private void readUserInputApp(String option, String email) throws InfraException {
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
                showMenuApp(email);
                break;
        }
    }

    private void showAdminMenu() throws InfraException {
        try {
            cursoManager = new CursoManager(null);
            questionarioManager = new QuestionarioManager(null);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        String option = JOptionPane.showInputDialog("Seção admin. Escolha a opção desejada:\n"
                + "1- Adicionar Curso\n"
                + "2- Visualizar Cursos\n"
                + "3- Remover Curso\n"
                + "4- Adicionar Pergunta\n"
                + "5- Visualizar Perguntas\n"
                + "6- Remover Pergunta\n"
                + "7- Sair");

        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                adicionarCurso();
                break;
            case 2:
                visualizarCursos();
                break;
            case 3:
                removerCurso();
                break;
            case 4:
                adicionarPergunta();
                break;
            case 5:
                visualizarPerguntas();
                break;
            case 6:
                removerPergunta();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showAdminMenu();
                break;
        }
    }

    private void adicionarCurso() {
        // Implementação para adicionar um curso
    }

    private void visualizarCursos() {
         // Implementação para visualizar os cursos
    }

    private void removerCurso() {
        // Implementação para remover um curso
    }

    private void adicionarPergunta() {
        // Implementação para adicionar uma pergunta
    }

    private void visualizarPerguntas() {
       // Implementação para visualizar as perguntas
    }

    private void removerPergunta() {
        // Implementação para remover uma pergunta
    }
    // (…)

    public void readUserInputLogin(String option) throws LoginInvalidException, InfraException {
        try {
            userManager = new UserManager(null);
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
        // Implementa o login do usuário
        boolean loggedIn = false;
        
        while (!loggedIn) {
            String email = JOptionPane.showInputDialog("Email do usuario:");
            if (email == null) {
                showMenuLogin();
                return;
            }

            String password = JOptionPane.showInputDialog("Senha do usuario:");
            if (password == null) {
                showMenuLogin();
                return;
            }

            loggedIn = loginValidator.checkUserLogin(email,password);

            if (loggedIn) {
                if (email.equals("admin@admin.com") && password.equals("admin012")) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    showAdminMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                    showMenuApp(email);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Por favor, tente novamente.");
            }
        }
    }

    public static TelaUsuario getInstance() throws InfraException{

        if(instance == null){
            instance = new TelaUsuario();
        }
        
        return instance;
    }

}
