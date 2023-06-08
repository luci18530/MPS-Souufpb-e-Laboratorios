package view;

import java.io.IOException;
import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import infra.InfraException;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    
    public AdminMenu() throws InfraException, IOException{
        try {
            cursoManager = new CursoManager();
            questionarioManager = new QuestionarioManager();

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
                + "7- Sair");

        int choice = Integer.parseInt(option);
        switch (choice) {
            case 1:
                cursoManager.addCurso();
                showMenu();
                break;
            case 2:
                cursoManager.showCursos();
                showMenu();
                break;
            case 3:
                cursoManager.removeCurso();
                showMenu();
                break;
            case 4:
                questionarioManager.addQuestionario();
                showMenu();
                break;
            case 5:
                questionarioManager.showQuestionarios();
                showMenu();
                break;
            case 6:
                questionarioManager.removeQuestionario();
                showMenu();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                showMenu();
                break;
        }
    }
}