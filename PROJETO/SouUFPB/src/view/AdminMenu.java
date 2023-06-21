package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import infra.InfraException;
import infra.Memento;
import infra.Caretaker;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    private static Caretaker history;
    private static Memento savedState;
    
    public AdminMenu() throws InfraException, IOException{
        try {
            cursoManager = new CursoManager();
            questionarioManager = new QuestionarioManager();
            history = new Caretaker();  

        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
    }

    public void showMenu() throws InfraException {
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
                showMenu();
                break;
            case "2":
                cursoManager.showCursos();
                showMenu();
                break;
            case "3":
                cursoManager.remove();
                history.addMemento(cursoManager.save());
                showMenu();
                break;
            case "4":
                questionarioManager.add();
                history.addMemento(questionarioManager.save());
                showMenu();
                break;
            case "5":
                questionarioManager.showQuestionarios();
                showMenu();
                break;
            case "6":
                questionarioManager.remove();
                history.addMemento(questionarioManager.save());
                showMenu();
                break;
            case "7":
                undo();
                showMenu();
                break;
            case "8":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenu();
                break;
        }
    }

    private static void undo() throws InfraException{
        
        savedState = history.getMemento();
        if (savedState.getOriginator().equals(cursoManager)) {
            cursoManager.restore(savedState);
        }
        else{
            questionarioManager.restore(savedState);
        }
        
        JOptionPane.showMessageDialog(null, "Inserção/Exclusão desfeita com sucesso!");
    }
}