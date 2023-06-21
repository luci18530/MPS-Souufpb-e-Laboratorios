package business.control;
import java.util.List;
import java.util.Map;

import business.model.Questionario;
import business.model.User;
import infra.InfraException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class UserAccessReport {

    protected abstract int getUniqueAccessCount(String user);

    protected abstract FileWriter openFile(String fileName);

    protected abstract void closeFile(String fileName);

    protected abstract void generateBody(String username, int totalAccessCount, String timeStamp);

    public final void generateReport(String fileName) throws InfraException, IOException{

        FileWriter fileWriter = openFile(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        UserManager userManager = new UserManager();
        Map<String, User> users = userManager.list();

        for (User user : users.values()) {
            int totalAccessCount = user.getTotalAccessCount();
            String timeStamp = user.getTimestamp();
            String username = user.getLogin();
            generateBody(username, totalAccessCount, timeStamp);
        }
        closeFile(fileName);
    }
}
