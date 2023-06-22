package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.CursoStrategy;
import business.control.ModelManager;
import business.control.QuestionarioStrategy;
import business.control.UserAccessReportGenerator;
import infra.InfraException;
import infra.Caretaker;

public class AdminMenu {
    private static AdminMenu instance = null;
    private static CursoStrategy cursoStrategy;
    private static ModelManager modelManager;
    private static QuestionarioStrategy questionarioStrategy;
    private static UserAccessReportGenerator reportGenerator;
    private static Caretaker history;
    
    public AdminMenu() throws InfraException, IOException{
        try {
            cursoStrategy = new CursoStrategy();
            questionarioStrategy = new QuestionarioStrategy();
            reportGenerator = new UserAccessReportGenerator();
            history = new Caretaker();
            modelManager = new ModelManager();  

        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }

    public void showMenu() throws InfraException, IOException {
        String option = JOptionPane.showInputDialog("Seção admin. Escolha a opção desejada:\n"
                + "1- Adicionar Curso\n"
                + "2- Visualizar Cursos\n"
                + "3- Remover Curso\n"
                + "4- Adicionar Pergunta\n"
                + "5- Visualizar Perguntas\n"
                + "6- Remover Pergunta\n"
                + "7- Gerar relatório\n"
                + "8- Desfazer ultima inserção/exclusão\n\n"
                + "9- Sair");

        //int choice = Integer.parseInt(option);
        if (option == null) return;
        switch (option) {
            case "1":

                modelManager.setStrategy(cursoStrategy);
                modelManager.add();                
                history.addMemento(modelManager.save());
                showMenu();
                break;
            case "2":
                cursoStrategy.showCursos();
                showMenu();
                break;
            case "3":
                modelManager.setStrategy(cursoStrategy);
                modelManager.remove();                
                history.addMemento(modelManager.save());
                showMenu();
                break;
            case "4":
                modelManager.setStrategy(questionarioStrategy);
                modelManager.add();                
                history.addMemento(modelManager.save());
                showMenu();
                break;
            case "5":
                questionarioStrategy.showQuestionarios();
                showMenu();
                break;
            case "6":
                modelManager.setStrategy(questionarioStrategy);
                modelManager.remove();                
                history.addMemento(modelManager.save());
                showMenu();
                break;
            case "7":
                reportGenerator.handleReport();
                showMenu();
                break;
            case "8":
                history.undo();
                showMenu();
                break;
            case "9":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenu();
                break;
        }
    }

    public static AdminMenu getInstance() throws InfraException, IOException {
        
        if (instance == null) {
            instance = new AdminMenu();
        }
        
        return instance;
    }
}