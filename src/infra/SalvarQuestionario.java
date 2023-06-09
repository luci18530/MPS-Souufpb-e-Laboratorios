package infra;

import java.io.IOException;
import java.util.Map;

import business.model.Questionario;

public class SalvarQuestionario implements SaveCommand<Questionario>{
    
    private FileManager<Questionario> fileManager;
    private final String PATH = "SouUFPB/src/database/questionarios.bin";

    public SalvarQuestionario() throws IOException{
        this.fileManager = new FileManager<Questionario>();
    }

    public void execute(Map<String,Questionario> data){
        this.fileManager.save(data, PATH);
    }
}
