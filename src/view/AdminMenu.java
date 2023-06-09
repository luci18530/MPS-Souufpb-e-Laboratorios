package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import infra.InfraException;
import infra.Caretaker;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    private static Caretaker history;
    
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

        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                history.backup(cursoManager);
                cursoManager.add();
                showMenu();
                break;
            case 2:
                history.backup(cursoManager);
                cursoManager.showCursos();
                showMenu();
                break;
            case 3:
                history.backup(cursoManager);
                cursoManager.remove();
                showMenu();
                break;
            case 4:
                history.backup(questionarioManager);
                questionarioManager.add();
                showMenu();
                break;
            case 5:
                history.backup(questionarioManager);
                questionarioManager.showQuestionarios();
                showMenu();
                break;
            case 6:
                history.backup(questionarioManager);
                questionarioManager.remove();
                showMenu();
                break;
            case 7:
                undo();
                showMenu();
                break;
            case 8:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenu();
                break;
        }
    }

    private static void undo() throws InfraException{
        history.undo();
        JOptionPane.showMessageDialog(null, "Inserção/Exclusão desfeita com sucesso!");
    }
}