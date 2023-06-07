package view;

import java.util.Map;

import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import business.model.Curso;
import business.model.Questionario;
import factory.CursoFactory;
import factory.CursoFactoryImpl;
import factory.QuestionarioFactory;
import factory.QuestionarioFactoryImpl;
import infra.InfraException;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    public static void show() throws InfraException{
        try {
            CursoFactory cursoFactory = new CursoFactoryImpl();
            cursoManager = new CursoManager(cursoFactory);
            QuestionarioFactory questionarioFactory = new QuestionarioFactoryImpl();
            questionarioManager = new QuestionarioManager(questionarioFactory);

        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
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
                show();
                break;
        }
    }

    private static void adicionarCurso() throws InfraException {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade do curso:");
        String centro = JOptionPane.showInputDialog("Digite o centro do curso:");
        String area = JOptionPane.showInputDialog("Digite a área do curso:");
    
        String[] cursoArgs = {nome, cidade, centro, area};
        cursoManager.adicionarCurso(cursoArgs);
        JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso!");
        show();
    }
    
    private static void visualizarCursos() throws InfraException {
        try {
            Map<String, Curso> cursos = cursoManager.getCursos();
            String listaDeCursos = "";
            for (Curso curso : cursos.values()) {
                listaDeCursos = listaDeCursos + "[ Nome: " + curso.getNome() + " | Cidade: " + curso.getCidade() + " | Centro: " + curso.getCentro() + "| Área: " + curso.getArea() + " ]" + "\n";
            }
            JOptionPane.showMessageDialog(null, listaDeCursos);
            
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        show();
    }
    
    
    private static void removerCurso() throws InfraException {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso que deseja remover:");
        try {
            cursoManager.removerCurso(nome);
            JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        show();
    }
    
    private static void adicionarPergunta() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta:");
        String area = JOptionPane.showInputDialog("Digite a área da pergunta:");
        try {
            questionarioManager.adicionarQuestionario(pergunta, area);
            JOptionPane.showMessageDialog(null, "Pergunta adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        show();
    }
    
    private static void visualizarPerguntas() throws InfraException {
        try {
            Map<String, Questionario> questionarios = questionarioManager.getQuestionarios();
            String listaDePerguntas = "";
            for (Questionario questionario : questionarios.values()) {
                listaDePerguntas = listaDePerguntas + "[ Pergunta: " + questionario.getPergunta() + " | Área: " + questionario.getArea() + " ]" + "\n";
            }
            JOptionPane.showMessageDialog(null, listaDePerguntas);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        show();
    }
    
    private static void removerPergunta() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta que deseja remover:");
        try {
            questionarioManager.removerQuestionario(pergunta);
            JOptionPane.showMessageDialog(null, "Pergunta removida com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        show();
    }
    
    }
    

