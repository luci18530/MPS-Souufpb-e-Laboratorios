package infra;

import java.util.Stack;

import business.control.Manager;

public class Caretaker{
    private Stack<Memento<?>> mementos;
    private Stack<Manager<?>> originators;

    public Caretaker(){
        mementos = new Stack<>();
        originators = new Stack<>();
    }

    public void backup(Manager<?> manager) throws InfraException{
        originators.push(manager);
        mementos.push(manager.save());
    }

    public void undo() throws InfraException{
        if(mementos.isEmpty()){
            return;
        }
        Memento<?> popped = mementos.pop();
        originators.pop().restore(popped);
    }
}