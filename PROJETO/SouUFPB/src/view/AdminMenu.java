package view;

import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import business.model.Curso;
import business.model.Questionario;
import infra.InfraException;
import java.util.Map;

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
                addCurso();
                break;
            case 2:
                cursoManager.showCursos();
                showMenu();
                break;
            case 3:
                removeCurso();
                break;
            case 4:
                addQuestionario();
                break;
            case 5:
                showQuestionarios();
                break;
            case 6:
                removeQuestionario();
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
    private void addCurso() throws InfraException {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade do curso:");
        String centro = JOptionPane.showInputDialog("Digite o centro do curso:");
        String area = JOptionPane.showInputDialog("Digite a área do curso:");

        String[] cursoArgs = {nome, cidade, centro, area};
        cursoManager.adicionarCurso(cursoArgs);
        JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso!");
        showMenu();
    }


    private void removeCurso() throws InfraException {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso que deseja remover:");
        try {
            cursoManager.removerCurso(nome);
            JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        showMenu();
    }

    private void addQuestionario() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta:");
        String area = JOptionPane.showInputDialog("Digite a área da pergunta:");
        try {
            questionarioManager.adicionarQuestionario(pergunta, area);
            JOptionPane.showMessageDialog(null, "Pergunta adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        showMenu();
    }

    private void showQuestionarios() throws InfraException {
        try {
            Map<String, Questionario> questionarios = questionarioManager.getQuestionarios();
            StringBuilder listaDePerguntas = new StringBuilder();
            for (Questionario questionario : questionarios.values()) {
                listaDePerguntas.append("[ Pergunta: ").append(questionario.getPergunta()).append(" | Área: ").append(questionario.getArea()).append(" ]\n");
            }
            JOptionPane.showMessageDialog(null, listaDePerguntas);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        showMenu();
    }

    private void removeQuestionario() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta que deseja remover:");
        try {
            questionarioManager.removerQuestionario(pergunta);
            JOptionPane.showMessageDialog(null, "Pergunta removida com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        showMenu();
    }
}