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
import infra.ConcreteMemento;
import infra.Memento;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import java.util.logging.Handler;

// Definindo a classe CursoManager
public class CursoStrategy implements Strategy<Curso> {
    // Inicializando um Map para armazenar os cursos e uma instância da classe CursoFile

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private SalvarCurso salvarCurso;
    private LoadCursos loadCursos;
    private static LoadCommandInvoker<Curso> loadCommandInvoker = new LoadCommandInvoker<>();
    private static SaveCommandInvoker<Curso> saveCommandInvoker = new SaveCommandInvoker<>();
    private static Map<String, Curso> cursos;
    private CursoFactory cursoFactory = new CursoFactoryImpl();

    // Construtor da classe que lança uma exceção do tipo InfraException
    public CursoStrategy() throws InfraException, IOException{
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
        loadFile();   
        
    }

    // Método para adicionar um curso
    public void add() throws InfraException{
        // Adicionando um novo Curso no Map 'cursos' e salvando no arquivo
        String nome = JOptionPane.showInputDialog("Digite o nome do curso:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade do curso:");
        String centro = JOptionPane.showInputDialog("Digite o centro do curso:");
        String area = JOptionPane.showInputDialog("Digite a área do curso:");
        String[] cursoArgs = {nome, cidade, centro, area};
        Curso curso = cursoFactory.createCurso(cursoArgs[0], cursoArgs[1], cursoArgs[2], cursoArgs[3]);
        cursos.put(cursoArgs[0], curso);
        saveCommandInvoker.invoke(cursos);
    }

    // Método para remover um curso
    public void remove() throws InfraException{
        String nome = JOptionPane.showInputDialog("Digite o nome do curso que deseja remover:");
        // Verifica se o curso existe no Map antes de tentar remover
        if(!cursos.containsKey(nome)){
            throw new IllegalArgumentException("Curso não encontrado. Tente novamente.");
        }

        // Remove o curso do Map 'cursos' e salva no arquivo
        try{cursos.remove(nome);
        saveCommandInvoker.invoke(cursos);
        JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
        } catch (IllegalArgumentException | InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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

    public List<Curso> getCursosPorArea(String area) throws InfraException {
        return this.list().values().stream()
            .filter(curso -> curso.getArea().equals(area))
            .collect(Collectors.toList());    

    }

    public void showCursos() throws InfraException {
        try {
            Map<String, Curso> cursos = list();
            StringBuilder listaDeCursos = new StringBuilder();
            for (Curso curso : cursos.values()) {
                listaDeCursos.append("[ Nome: ").append(curso.getNome()).append(" | Cidade: ").append(curso.getCidade()).append(" | Centro: ").append(curso.getCentro()).append(" | Área: ").append(curso.getArea()).append(" ]\n");
            }
            JOptionPane.showMessageDialog(null, listaDeCursos);
        } catch (InfraException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }

    @Override
    public void restore(Memento memento) throws InfraException {
        cursos = (Map<String, Curso>) memento.getState();
        saveCommandInvoker.invoke(cursos);
    }

    @Override
    public Memento save() throws InfraException {
        return new ConcreteMemento<>(cursos,this);
    }

    public static void updateFile() throws InfraException {
		saveCommandInvoker.invoke(cursos);
	}

	public static void loadFile() throws InfraException{
		loadCommandInvoker.invoke();
	}
}
