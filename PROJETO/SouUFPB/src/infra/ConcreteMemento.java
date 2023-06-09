package infra;

import java.util.Map;

import business.control.Manager;

public class ConcreteMemento<T> implements Memento{

    private Map<String, ?> state;
    private Manager<T> originator;

    public ConcreteMemento(Map<String, ?> state, Manager<T> originator){
        this.state = state;
        this.originator = originator;
    }

    public Map<String, ?> getState() {
        return this.state;
    }

    public Manager<T> getOriginator() {
        return originator;
    }
}