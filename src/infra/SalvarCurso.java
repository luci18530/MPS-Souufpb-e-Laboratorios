package infra;

import java.io.IOException;
import java.util.Map;

import business.model.Curso;

public class SalvarCurso implements SaveCommand<Curso>{
    
    private FileManager<Curso> fileManager;
    private final String PATH = "SouUFPB/src/database/cursos.bin";

    public SalvarCurso() throws IOException{
        this.fileManager = new FileManager<Curso>();
    }

    public void execute(Map<String, Curso> data){
        fileManager.save(data, PATH);
    }
}
