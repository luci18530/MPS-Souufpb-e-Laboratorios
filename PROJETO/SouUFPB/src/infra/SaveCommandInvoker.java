package infra;

import java.util.Map;

public class SaveCommandInvoker<T>{
    private SaveCommand<T> command;
    
    public SaveCommandInvoker(){
    }

    public void setCommand(SaveCommand<T> command) {
        this.command = command;
    }

    public void invoke(Map<String,T> data) throws InfraException{
        command.execute(data);
    }

}
