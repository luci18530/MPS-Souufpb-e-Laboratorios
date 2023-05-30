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
    // Criação de um Logger estático para a classe
    private static Logger logger = Logger.getLogger(CursoFile.class.getName());
    
    public CursoFile(){

        try {
            
            // Criando manipuladores para o Logger: um para o console e outro para um arquivo
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("relatorioCursos.txt");

            // Definindo o nível dos manipuladores para todos os níveis (Level.ALL)
            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            // Adicionando os manipuladores ao Logger
            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            // Desativando o uso de manipuladores do Logger pai
            logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            // Registra a exceção no logger se houver um erro de IO na criação do manipulador do arquivo
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }
    }

    public void salvarCursos(Map<String, Curso> cursos){

        File file = new File("PROJETO/SouUFPB/src/database/Cursos.bin");
        try {
            // Criação do ObjectOutputStream para escrever os objetos no arquivo
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(file));
            // Escrevendo os cursos no arquivo
            saida.writeObject(cursos);
            // Fechando o ObjectOutputStream
            saida.close();
        } catch (FileNotFoundException ex) {
            // Exibe a pilha de erros se o arquivo não for encontrado
            ex.printStackTrace();
        } catch(IOException ex){
            // Exibe a pilha de erros se ocorrer um erro de IO
            ex.printStackTrace();
        }
    }

    public Map<String,Curso> carregarCursos() throws InfraException {
        
        Map<String, Curso> cursos = new HashMap<String,Curso>();
        File file = new File("PROJETO/SouUFPB/src/database/Cursos.bin");
        ObjectInputStream objetoEntrada = null;
        InputStream entrada = null;

        if (!file.exists()) {
            salvarCursos(cursos);
        }
        try {
            // Criação do FileInputStream para ler o arquivo
            entrada = new FileInputStream(file);

            // Criação do ObjectInputStream para ler os objetos do arquivo
            objetoEntrada = new ObjectInputStream(entrada);

            // Leitura dos cursos do arquivo
            cursos = (Map<String,Curso>) objetoEntrada.readObject();

            // Retorna os cursos lidos
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
        finally {
            try {
                if (objetoEntrada != null) {
                    objetoEntrada.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException ex) {
                logger.config(ex.getMessage());
                throw new InfraException("Erro de entrada. Contate o administrador ou tente novamente mais tarde");
            } catch(Exception ex){
                logger.severe(ex.getMessage());
                throw new InfraException("Erro Crítico do Sistema. Contate o administrador imediatamente.");
            }
        }
        
    }

    // Método para retornar o Logger
    public Logger getLogger(){
        return logger;
    }
}