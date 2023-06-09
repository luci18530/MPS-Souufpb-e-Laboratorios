package business.control;
import java.util.List;

public abstract class UserAccessReport {

    protected abstract List<String> getUsers();

    protected abstract int getTotalAccessCount(String user);

    protected abstract int getUniqueAccessCount(String user);

    protected abstract void generateHeader();

    protected abstract void generateBody(String user, int totalAccessCount, int uniqueAccessCount);

    protected abstract void generateFooter();

    public final void generateReport() {
        generateHeader();
        List<String> users = getUsers();
        for (String user : users) {
            int totalAccessCount = getTotalAccessCount(user);
            int uniqueAccessCount = getUniqueAccessCount(user);
            generateBody(user, totalAccessCount, uniqueAccessCount);
        }
        generateFooter();
    }
}
