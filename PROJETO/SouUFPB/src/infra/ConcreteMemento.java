package infra;

import java.util.Map;

import business.control.Strategy;

public class ConcreteMemento<T> implements Memento{

    private Map<String, ?> state;
    private Strategy<T> originator;

    public ConcreteMemento(Map<String, ?> state, Strategy<T> originator){
        this.state = state;
        this.originator = originator;
    }

    public Map<String, ?> getState() {
        return this.state;
    }

    public Strategy<T> getOriginator() {
        return originator;
    }
}