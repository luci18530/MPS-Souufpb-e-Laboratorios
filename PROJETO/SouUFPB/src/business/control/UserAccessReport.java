package business.control;
import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class UserAccessReport {

    protected abstract List<String> getUsers();

    protected abstract int getTotalAccessCount(String user);

    protected abstract int getUniqueAccessCount(String user);

    protected abstract FileWriter openFile(String fileName);

    protected abstract void closeFile(String fileName);

    protected abstract void generateBody(String user, int totalAccessCount, int uniqueAccessCount);

    public final void generateReport(String fileName) {

        FileWriter fileWriter = openFile(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> users = getUsers();
        for (String user : users) {
            int totalAccessCount = getTotalAccessCount(user);
            int uniqueAccessCount = getUniqueAccessCount(user);
            generateBody(user, totalAccessCount, uniqueAccessCount);
        }
        closeFile(fileName);
    }
}
