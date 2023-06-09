package view;

import java.util.Map;

import javax.swing.JOptionPane;
import business.control.CursoManager;
import business.control.QuestionarioManager;
import business.model.Curso;
import infra.Caretaker;
import infra.InfraException;

public class AdminMenu {
    private static CursoManager cursoManager;
    private static QuestionarioManager questionarioManager;
    private static Caretaker history;
    
    public static void show() throws InfraException{
        try {
            cursoManager = new CursoManager();
            questionarioManager = new QuestionarioManager();
            history = new Caretaker();
            

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
                + "7- Desfazer ultima inserção/exclusão\n"
                + "8- Sair");

        //int choice = Integer.parseInt(option);
        if (option == null) return;
        switch (option) {
            case "1":
                //history.backup(cursoManager);
                adicionarCurso();
                AdminMenu.show();
                break;
            case "2":
                history.backup(cursoManager);
                visualizarCursos();
                AdminMenu.show();
                break;
            case "3":
                history.backup(cursoManager);
                removerCurso();
                AdminMenu.show();
                break;
            case "4":
                history.backup(questionarioManager);
                adicionarPergunta();
                AdminMenu.show();
                break;
            case "5":
                visualizarPerguntas();
                AdminMenu.show();
                break;
            case "6":
                history.backup(questionarioManager);
                removerPergunta();
                AdminMenu.show();
                break;
            case "7":
                undo();
                AdminMenu.show();
                break;
            case "8":
                System.exit(0);
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
    
        String[] cursoArgs = {nome, cidade, centro};
        cursoManager.add(cursoArgs);
        JOptionPane.showMessageDialog(null, "Curso adicionado com sucesso!");
    }
    
    private static void visualizarCursos() {
        try {
            Map<String, Curso> cursos = cursoManager.list();
            String listaDeCursos = "";
            for (Curso curso : cursos.values()) {
                listaDeCursos = listaDeCursos + "[ Nome: " + curso.getNome() + " | Cidade: " + curso.getCidade() + " | Centro: " + curso.getCentro() + " ]" + "\n";
            }
            JOptionPane.showMessageDialog(null, listaDeCursos);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    
    private static void removerCurso() {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso que deseja remover:");
        try {
            cursoManager.remove(nome);
            JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void undo() throws InfraException{
        history.undo();
        JOptionPane.showMessageDialog(null, "Inserção/Exclusão desfeita com sucesso!");
    }
    
    private static void adicionarPergunta() {
        // Implementação para adicionar uma pergunta
    }

    private static void visualizarPerguntas() {
       // Implementação para visualizar as perguntas
    }

    private static void removerPergunta() {
        // Implementação para remover uma pergunta
    }



}
    

