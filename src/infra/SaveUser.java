package infra;

import java.io.IOException;
import java.util.Map;

import business.model.User;

public class SaveUser implements SaveCommand<User> {
    
    private FileManager<User> fileManager;
    private final String PATH = "SouUFPB/src/database/user.bin";

    public SaveUser() throws InfraException, IOException{
        this.fileManager = new FileManager<User>();
    }

    @Override
    public void execute(Map<String, User> data) {
        this.fileManager.save(data, PATH);
    }

    

}
