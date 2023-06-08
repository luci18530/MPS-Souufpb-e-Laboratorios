package business.control;

import infra.InfraException;
import infra.LoadCommandInvoker;
import infra.LoadQuestionario;
import infra.SalvarQuestionario;
import infra.SaveCommandInvoker;
import business.model.Questionario;
import factory.QuestionarioFactory;
import factory.QuestionarioFactoryImpl;
import java.io.IOException;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


public class QuestionarioManager {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private LoadQuestionario loadQuestionario;
    private SalvarQuestionario salvarQuestionario;
    private SaveCommandInvoker<Questionario> saveCommandInvoker = new SaveCommandInvoker<>();
    private LoadCommandInvoker<Questionario> loadCommandInvoker = new LoadCommandInvoker<>();
    private Map<String, Questionario> questionarios;
    private QuestionarioFactory questionarioFactory = new QuestionarioFactoryImpl(); 

    public QuestionarioManager() throws InfraException, IOException{
        
        try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("QuestionarioManagerLog.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }
        
        salvarQuestionario = new SalvarQuestionario();
        loadQuestionario = new LoadQuestionario();
        loadCommandInvoker.setCommand(loadQuestionario);
        saveCommandInvoker.setCommand(salvarQuestionario);

        questionarios = loadCommandInvoker.invoke();
    }

    public void adicionarQuestionario(String pergunta, String area) throws InfraException{
        if(questionarios.containsKey(pergunta)){
            throw new IllegalArgumentException("Pergunta já cadastrada. Tente novamente.");
        }
        Questionario questionario = questionarioFactory.createQuestionario(pergunta, area);
        questionarios.put(pergunta, questionario);
        saveCommandInvoker.invoke(questionarios);
    }

    public void removerQuestionario(String pergunta) throws InfraException{
        if(!questionarios.containsKey(pergunta)){
            throw new IllegalArgumentException("Pergunta não encontrada. Tente novamente.");
        }

        questionarios.remove(pergunta);
        saveCommandInvoker.invoke(questionarios);
    }

    public Map<String,Questionario> getQuestionarios() throws InfraException{

        try{
            Map<String,Questionario> meusQuestionarios = this.loadCommandInvoker.invoke();
            return meusQuestionarios;

        } catch(NullPointerException ex){
            logger.severe(ex.getMessage());
            throw new InfraException("Erro na exibição. Contate o administrador ou tente mais tarde.");
        }
    }

    public void addQuestionario() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta:");
        String area = JOptionPane.showInputDialog("Digite a área da pergunta:");
        try {
            adicionarQuestionario(pergunta, area);
            JOptionPane.showMessageDialog(null, "Pergunta adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public void showQuestionarios() throws InfraException {
        try {
            Map<String, Questionario> questionarios = getQuestionarios();
            StringBuilder listaDePerguntas = new StringBuilder();
            for (Questionario questionario : questionarios.values()) {
                listaDePerguntas.append("[ Pergunta: ").append(questionario.getPergunta()).append(" | Área: ").append(questionario.getArea()).append(" ]\n");
            }
            JOptionPane.showMessageDialog(null, listaDePerguntas);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    public void removeQuestionario() throws InfraException {
        String pergunta = JOptionPane.showInputDialog("Digite a pergunta que deseja remover:");
        try {
            removerQuestionario(pergunta);
            JOptionPane.showMessageDialog(null, "Pergunta removida com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}