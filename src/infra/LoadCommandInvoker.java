package infra;

import java.util.Map;

public class LoadCommandInvoker<T> {
    private LoadCommand<T> command;

    public void setCommand(LoadCommand<T> command){
        this.command = command;
    }

    public Map<String, T> invoke() throws InfraException{
        return command.execute();
    }
}
