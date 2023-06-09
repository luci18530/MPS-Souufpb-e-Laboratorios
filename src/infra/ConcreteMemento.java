package infra;

import java.util.Map;

public class ConcreteMemento<T> implements Memento<T>{

    private Map<String, T> state;

    public ConcreteMemento(Map<String, T> state){
        this.state = state;
    }

    @Override
    public Map<String, T> getState() {
        return this.state;
    }
}