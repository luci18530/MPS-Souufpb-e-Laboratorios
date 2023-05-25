package business.control;

import infra.InfraException;
import infra.QuestionarioFile;
import business.model.Questionario;
import factory.CustomFile;
import factory.FileFactory;

import java.util.Map;
import java.util.logging.Logger;

public class QuestionarioManager {
    private Map<String, Questionario> questionarios;
    private CustomFile questionarioFile;

    public QuestionarioManager(FileFactory factory) throws InfraException{
        questionarioFile = factory.create();
        questionarios = questionarioFile.carregarQuestionarios();
    }

    public void adicionarQuestionario(String pergunta, String area){
        questionarios.put(pergunta, new Questionario(pergunta, area));
        questionarioFile.salvarQuestionarios(questionarios);
    }

    public void removerQuestionario(String pergunta) throws InfraException{
        if(!questionarios.containsKey(pergunta)){
            throw new IllegalArgumentException("Pergunta não encontrada. Tente novamente.");
        }

        questionarios.remove(pergunta);
        questionarioFile.salvarQuestionarios(questionarios);
    }

    public Map<String,Questionario> getQuestionarios() throws InfraException{
        Logger logger = questionarioFile.getLogger();

        try{
            Map<String,Questionario> meusQuestionarios = questionarioFile.carregarQuestionarios();
            return meusQuestionarios;

        } catch(NullPointerException ex){
            logger.severe(ex.getMessage());
            throw new InfraException("Erro na exibição. Contate o administrador ou tente mais tarde.");
        }
    }
}