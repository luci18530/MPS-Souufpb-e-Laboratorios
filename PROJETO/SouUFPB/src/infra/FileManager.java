package infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

public class FileManager<T> {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public FileManager(){

        try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("relatorioLog.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }

    }

    public void save(Map<String, T> users, String path){

        File file = new File(path);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(users);
            out.close();        
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        } catch (IOException ex){
        	ex.printStackTrace();
        }
        
    }

    public Map<String,T> load(String path) throws InfraException{

        Map<String, T> hash = new HashMap<String,T>();
    	File file = new File(path);
        ObjectInputStream objInput = null;
        InputStream in = null;

        if(!file.exists()){
        		save(hash,path);
        }
        try {
            in = new FileInputStream(file);
            //Recupera a lista
            objInput = new ObjectInputStream(in);
            hash = ( Map<String, T>) objInput.readObject();
            in.close();
          

            return hash;
        
        } catch (NullPointerException ex){
            logger.config(ex.getMessage());
            throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
           
        } catch (IOException ex){
            logger.config(ex.getMessage());
            throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
         } catch (ClassNotFoundException ex) {
            logger.config(ex.getMessage());
            throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");      
        }
        finally {
        	try {
				objInput.close();
				in.close();
			
        		} catch (IOException e) {
				logger.severe(e.getMessage());
	            throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
	            
			} catch (Exception e) {
				logger.severe(e.getMessage());
	            throw new InfraException("Erro de persistencia, contacte o admin ou tente mais tarde");
	            
			} 
  			
        }
    }

    public Logger getLogger() {
        return logger;
    }

}

