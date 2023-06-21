package business.control;
import java.util.Map;

import business.model.User;
import infra.InfraException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class UserAccessReport {

    protected abstract FileWriter openFile(String fileName);
    protected abstract void generateHeader(BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateBody(User user, BufferedWriter bufferedWriter) throws IOException;
    protected abstract void generateFooter(BufferedWriter bufferedWriter) throws IOException;

    public final void generateReport(String fileName) throws InfraException, IOException{

        FileWriter fileWriter = openFile(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        generateHeader(bufferedWriter);
        
        UserManager userManager = new UserManager();
        Map<String, User> users = userManager.list();

        for (User user : users.values()) {
            generateBody(user, bufferedWriter);
        }

        generateFooter(bufferedWriter);

        bufferedWriter.close();
        fileWriter.close();
    }
}
