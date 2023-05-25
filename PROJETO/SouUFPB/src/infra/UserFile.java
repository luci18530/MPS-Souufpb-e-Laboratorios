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
import business.model.Questionario;
import business.model.User;
import factory.CustomFile;


public class UserFile implements CustomFile {
	
	private static Logger logger = Logger.getLogger(UserFile.class.getName());

	public UserFile() {
		
		try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("relatorioLog.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            UserFile.logger.addHandler(hdConsole);
            UserFile.logger.addHandler(hdArquivo);

            UserFile.logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }
	}
    public void saveUsers(Map<String,User> users){
        File file = new File("user.bin");
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
    public Map<String, User> loadUsers() throws InfraException{
    	Map<String, User> users = new HashMap<String,User>();
    	File file = new File("user.bin");
        ObjectInputStream objInput = null;
        InputStream in = null;
        if(!file.exists()){
        		saveUsers(users);
        }
        try {
            in = new FileInputStream(file);
            //Recupera a lista
            objInput = new ObjectInputStream(in);
            users = ( Map<String, User>) objInput.readObject();
            in.close();
          

            return users;
        
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

    public Logger getLogger(){
        return logger;
    }
    @Override
    public Map<String, Curso> carregarCursos() throws InfraException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'carregarCursos'");
    }
    @Override
    public void salvarCursos(Map<String, Curso> cursos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarCursos'");
    }
    @Override
    public Map<String, Questionario> carregarQuestionarios() throws InfraException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'carregarQuestionarios'");
    }
    @Override
    public void salvarQuestionarios(Map<String, Questionario> questionarios) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarQuestionarios'");
    }
}
