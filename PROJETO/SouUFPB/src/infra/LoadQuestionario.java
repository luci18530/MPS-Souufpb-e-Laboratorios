package infra;

import java.io.IOException;
import java.util.Map;

import business.model.Questionario;

public class LoadQuestionario implements LoadCommand<Questionario>{
    
    private FileManager<Questionario> fileManager;
    private final String PATH = "SouUFPB/src/database/questionarios.bin";

    public LoadQuestionario() throws IOException{
        this.fileManager = new FileManager<Questionario>();
    }

    @Override
    public Map<String, Questionario> execute() throws InfraException {
        return this.fileManager.load(PATH);
    }

    

    
}
