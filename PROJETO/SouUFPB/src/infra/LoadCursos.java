package infra;

import java.io.IOException;
import java.util.Map;

import business.model.Curso;

public class LoadCursos implements LoadCommand<Curso>{
    
    private FileManager<Curso> fileManager;
    private final String PATH = "SouUFPB/src/database/cursos.bin";

    public LoadCursos() throws IOException{
        this.fileManager = new FileManager<Curso>();
    }

    @Override
    public Map<String, Curso> execute() throws InfraException {
        return this.fileManager.load(PATH);
    }

    
}
