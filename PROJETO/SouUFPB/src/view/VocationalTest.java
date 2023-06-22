package view;

import business.control.*;
import business.model.*;
import infra.InfraException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class VocationalTest {
    private UserStrategy userStrategy;
    private QuestionarioStrategy questionarioStrategy;
    private CursoStrategy cursoStrategy;
    private ModelManager modelManager;

    public VocationalTest() throws InfraException, IOException{
        userStrategy = new UserStrategy();
        questionarioStrategy = new QuestionarioStrategy();
        cursoStrategy = new CursoStrategy();
        modelManager = new ModelManager();
    }

    public void doVocationalTest(String email) throws InfraException {
        // Recupera o questionário e o resultado do usuário
        Map<String, Questionario> questionarios = questionarioStrategy.list();
        Resultado resultado = userStrategy.getUserResult(email);
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

        userStrategy.setUserResult(email, resultado);
        JOptionPane.showMessageDialog(null, resultadoToString(resultado));

        // Exibe o resultado do teste
        System.out.println("Resultado do teste:");
        for (Map.Entry<String, Integer> entry : resultado.getScorePorArea().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        resultado = userStrategy.getUserResult(email);
        String areaComMaiorPontuacao = resultado.getAreaComMaiorPontuacao();
        List<Curso> cursosRecomendados = cursoStrategy.getCursosPorArea(areaComMaiorPontuacao);
    
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
