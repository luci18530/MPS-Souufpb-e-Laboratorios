package business.control;

import infra.InfraException;
import infra.CursoFile;
import business.model.Curso;
import java.util.Map;
import java.util.logging.Logger;

public class CursoManager {

    private Map<String, Curso> cursos;
    private CursoFile cursoFile;

    public CursoManager() throws InfraException{
        
        cursoFile = new CursoFile();
        cursos = cursoFile.carregarCursos();
    }

    public void adicionarCurso(String[] args){

        cursos.put(args[0], new Curso(args[0], args[1], args[2]));
        cursoFile.salvarCursos(cursos);
    }

    public void removerCurso(String nome) throws InfraException{

        if(!cursos.containsKey(nome)){
            throw new IllegalArgumentException("Curso não encontrado. Tente novamente.");
        }

        cursos.remove(nome);
        cursoFile.salvarCursos(cursos);
    }
    
    public Map<String,Curso> getCursos() throws InfraException{

        Logger logger = cursoFile.getLogger();
        
        try{
            Map<String,Curso> meusCursos = cursoFile.carregarCursos();
            return meusCursos;

        } catch(NullPointerException ex){
            logger.severe(ex.getMessage());
            throw new InfraException("Erro na exibição. Contate o admnistrador ou tente mais tarde.");
        }
    }

    
}
