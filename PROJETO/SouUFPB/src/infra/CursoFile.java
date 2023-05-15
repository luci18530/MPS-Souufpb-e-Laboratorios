package infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import business.model.Curso;

public class CursoFile {

    private static Logger logger = Logger.getLogger(CursoFile.class.getName());
    
    public CursoFile(){

        try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("relatorioCursos.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);

        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }
    }

    public void salvarCursos(Map<String, Curso> cursos){

        File file = new File("Cursos.txt");
        try {
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(file));
            saida.writeObject(cursos);
            saida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public Map<String,Curso> carregarCursos() throws InfraException {
        
        Map<String, Curso> cursos = new HashMap<String,Curso>();
        File file = new File("curso.bin");
        ObjectInputStream objetoEntrada = null;
        InputStream entrada = null;

        if (!file.exists()) {
            salvarCursos(cursos);
        }
        try {
            entrada = new FileInputStream(file);
            objetoEntrada = new ObjectInputStream(entrada);

            cursos = (Map<String,Curso>) objetoEntrada.readObject();

            return cursos;

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

            try{

                objetoEntrada.close();
                entrada.close();

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