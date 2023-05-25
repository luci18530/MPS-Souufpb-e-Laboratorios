package infra;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Questionario;

public class QuestionarioFile {
    private static Logger logger = Logger.getLogger(QuestionarioFile.class.getName());
    
    public QuestionarioFile(){

        try {
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("relatorioQuestionarios.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);

        } catch (IOException ex) {
            logger.severe("Ocorreu um erro no arquivo durante a execução do programa");
        }
    }

    public void salvarQuestionarios(Map<String, Questionario> questionarios){

        File file = new File("Questionarios.bin");
        try {
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(file));
            saida.writeObject(questionarios);
            saida.close();
        } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    
        public Map<String,Questionario> carregarQuestionarios() throws InfraException {
            
            Map<String, Questionario> questionarios = new HashMap<String,Questionario>();
            File file = new File("Questionarios.bin");
            ObjectInputStream objetoEntrada = null;
            InputStream entrada = null;
    
            if (!file.exists()) {
                salvarQuestionarios(questionarios);
            }
            try {
                entrada = new FileInputStream(file);
                objetoEntrada = new ObjectInputStream(entrada);
                questionarios = (Map<String,Questionario>) objetoEntrada.readObject();
                return questionarios;
    
            } catch(NullPointerException ex){
                logger.config(ex.getMessage());
                throw new InfraException("Erro de persistência. Contate o administrador ou tente mais tarde");
            } catch(IOException ex){
                logger.config(ex.getMessage());
                throw new InfraException("Erro na formatação do arquivo. Contate o administrador ou tente mais tarde.");
            } catch(ClassNotFoundException ex){
                logger.config(ex.getMessage());
                throw new InfraException("Erro na formatação do arquivo. Contate o administrador ou tente mais tarde.");
            }
            finally{
                try {
                    // Feche o ObjectInputStream e FileInputStream apenas se eles não forem nulos
                    if (objetoEntrada != null && entrada != null) {
                        objetoEntrada.close();
                        entrada.close();
                    }
                } catch(IOException ex){
                    logger.config(ex.getMessage());
                    throw new InfraException("Erro de entrada. Contate o administrador ou tente novamente mais tarde");
                } catch(Exception ex){
                    logger.severe(ex.getMessage());
                    throw new InfraException("Erro Crítico do Sistema. Contate o administrador imediatamente.");
                }
            }
        }
    
        public Logger getLogger(){
            return logger;
        }
    }
    