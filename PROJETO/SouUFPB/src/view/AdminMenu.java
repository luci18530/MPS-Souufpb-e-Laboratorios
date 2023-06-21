package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import infra.InfraException;
import infra.Caretaker;

public class AdminMenu {
    private static AdminMenu instance = null;
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    private static Caretaker history;
    
    private AdminMenu() throws InfraException, IOException{
        try {
            cursoManager = new CursoManager();
            questionarioManager = new QuestionarioManager();
            history = new Caretaker();  

        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }

    public void showMenu() throws InfraException, IOException{
        Home();
    }

    public void Home() throws InfraException {

        String option = JOptionPane.showInputDialog("Seção admin. Escolha a opção desejada:\n"
                + "1- Adicionar Curso\n"
                + "2- Visualizar Cursos\n"
                + "3- Remover Curso\n"
                + "4- Adicionar Pergunta\n"
                + "5- Visualizar Perguntas\n"
                + "6- Remover Pergunta\n"
                + "7- Desfazer ultima inserção/exclusão\n\n"
                + "8- Sair");

        //int choice = Integer.parseInt(option);
        if (option == null) return;
        switch (option) {
            case "1":
                cursoManager.add();
                history.addMemento(cursoManager.save());
                Home();
                break;
            case "2":
                cursoManager.showCursos();
                Home();
                break;
            case "3":
                cursoManager.remove();
                history.addMemento(cursoManager.save());
                Home();
                break;
            case "4":
                questionarioManager.add();
                history.addMemento(questionarioManager.save());
                Home();
                break;
            case "5":
                questionarioManager.showQuestionarios();
                Home();
                break;
            case "6":
                questionarioManager.remove();
                history.addMemento(questionarioManager.save());
                Home();
                break;
            case "7":
                history.undo();
                Home();
                break;
            case "8":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                Home();
                break;
        }
    }

    public static AdminMenu getInstance() throws InfraException, IOException{

        if(instance == null){
            instance = new AdminMenu();
        }

        return instance;
    }
}