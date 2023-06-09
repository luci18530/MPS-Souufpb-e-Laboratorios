package business.control;

import infra.ConcreteMemento;
// Importando as classes necessárias
import infra.InfraException;
import infra.LoadCommandInvoker;
import infra.LoadCursos;
import infra.Memento;
import infra.SalvarCurso;
import infra.SaveCommandInvoker;
import business.model.Curso;
import factory.CursoFactory;
import factory.CursoFactoryImpl;

import java.io.IOException;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.logging.Handler;

// Definindo a classe CursoManager
public class CursoManager implements Manager<Curso> {
    // Inicializando um Map para armazenar os cursos e uma instância da classe CursoFile

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private SalvarCurso salvarCurso;
    private LoadCursos loadCursos;
    private LoadCommandInvoker<Curso> loadCommandInvoker = new LoadCommandInvoker<>();;
    private SaveCommandInvoker<Curso> saveCommandInvoker = new SaveCommandInvoker<>();;
    private Map<String, Curso> cursos;
    private CursoFactory cursoFactory = new CursoFactoryImpl();

    // Construtor da classe que lança uma exceção do tipo InfraException
    public CursoManager() throws InfraException{
        // Instanciando um novo CursoFile e carregando os cursos do arquivo
        
        try {
            
            Handler hdConsole = new ConsoleHandler();
            Handler hdArquivo = new FileHandler("CursoManagerLog.txt");

            hdConsole.setLevel(Level.ALL);
            hdArquivo.setLevel(Level.ALL);

            logger.addHandler(hdConsole);
            logger.addHandler(hdArquivo);

            logger.setUseParentHandlers(false);


        } catch (IOException ex) {
            logger.severe("ocorreu um erro no arquivo durante a execução do programa");
        }

        salvarCurso = new SalvarCurso();
        loadCursos = new LoadCursos();

        saveCommandInvoker.setCommand(salvarCurso);
        loadCommandInvoker.setCommand(loadCursos);
        cursos = loadCommandInvoker.invoke();      
        
    }

    // Método para adicionar um curso
    public void add(String[] args) throws InfraException{
        // Adicionando um novo Curso no Map 'cursos' e salvando no arquivo
        Curso curso = cursoFactory.createCurso(args[0], args[1], args[2]);
        cursos.put(args[0], curso);
        saveCommandInvoker.invoke(cursos);
    }

    // Método para remover um curso
    public void remove(String nome) throws InfraException{
        // Verifica se o curso existe no Map antes de tentar remover
        if(!cursos.containsKey(nome)){
            throw new IllegalArgumentException("Curso não encontrado. Tente novamente.");
        }

        // Remove o curso do Map 'cursos' e salva no arquivo
        cursos.remove(nome);
        saveCommandInvoker.invoke(cursos);
    }

    // Método para retornar os cursos
    public Map<String,Curso> list() throws InfraException{

        try{
            // Carrega os cursos do arquivo
            Map<String,Curso> meusCursos = loadCommandInvoker.invoke();
            return meusCursos;

        } catch(NullPointerException ex){
            // Registra a exceção e lança uma nova InfraException
            logger.severe(ex.getMessage());
            throw new InfraException("Erro na exibição. Contate o admnistrador ou tente mais tarde.");
        }
    }

    @Override
    public void restore(Memento<?> memento) throws InfraException {
        cursos = (Map<String, Curso>) memento.getState();
        saveCommandInvoker.invoke(cursos);
    }

    @Override
    public Memento<Curso> save() throws InfraException {
        return new ConcreteMemento<>(cursos);
    }
}
