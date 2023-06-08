package infra;

import java.io.IOException;
import java.util.Map;

import business.model.User;

public class LoadUsers implements LoadCommand<User>{

    private FileManager<User> fileManager;
    private final String PATH = "SouUFPB/src/database/user.bin";

    public LoadUsers() throws IOException{
        this.fileManager = new FileManager<User>();
    }

    @Override
    public Map<String, User> execute() throws InfraException {
        return this.fileManager.load(PATH);
    }

    
}