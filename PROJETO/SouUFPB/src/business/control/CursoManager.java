package business.control;

// Importando as classes necessárias
import infra.InfraException;
import infra.LoadCommandInvoker;
import infra.LoadCursos;
import infra.SalvarCurso;
import infra.SaveCommandInvoker;
import business.model.Curso;
import factory.CursoFactory;
import factory.CursoFactoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.logging.Handler;

// Definindo a classe CursoManager
public class CursoManager {
    // Inicializando um Map para armazenar os cursos e uma instância da classe CursoFile

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private SalvarCurso salvarCurso;
    private LoadCursos loadCursos;
    private LoadCommandInvoker<Curso> loadCommandInvoker = new LoadCommandInvoker<>();
    private SaveCommandInvoker<Curso> saveCommandInvoker = new SaveCommandInvoker<>();
    private Map<String, Curso> cursos;
    private CursoFactory cursoFactory = new CursoFactoryImpl();

    // Construtor da classe que lança uma exceção do tipo InfraException
    public CursoManager() throws InfraException, IOException{
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
    public void adicionarCurso(String[] args) throws InfraException{
        // Adicionando um novo Curso no Map 'cursos' e salvando no arquivo
        Curso curso = cursoFactory.createCurso(args[0], args[1], args[2], args[3]);
        cursos.put(args[0], curso);
        saveCommandInvoker.invoke(cursos);
    }

    // Método para remover um curso
    public void removerCurso(String nome) throws InfraException{
        // Verifica se o curso existe no Map antes de tentar remover
        if(!cursos.containsKey(nome)){
            throw new IllegalArgumentException("Curso não encontrado. Tente novamente.");
        }

        // Remove o curso do Map 'cursos' e salva no arquivo
        cursos.remove(nome);
        saveCommandInvoker.invoke(cursos);
    }

    // Método para retornar os cursos
    public Map<String,Curso> getCursos() throws InfraException{

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

    public List<Curso> getCursosPorArea(String area) throws InfraException {
        return this.getCursos().values().stream()
            .filter(curso -> curso.getArea().equals(area))
            .collect(Collectors.toList());    

    }
}
