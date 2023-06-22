package business.control;

import java.util.Map;

import infra.InfraException;
import infra.Memento;

public class ModelManager {

    private Strategy<?> strategy;

    public void setStrategy(Strategy<?> strategy) {
        this.strategy = strategy;
    }

    public void add() throws InfraException{
        strategy.add();
    }

    public void remove() throws InfraException{
        strategy.remove();
    }

    public Map<String, ?> list() throws InfraException{
        return strategy.list();
    }

    public Memento save() throws InfraException{
        return strategy.save();
    }

    public void restore(Memento memento) throws InfraException{
        strategy.restore(memento);
    }

    public Strategy<?> getStrategy() {
        return strategy;
    }
    
}
