package business.control;

import java.util.Map;


import infra.InfraException;
import infra.Memento;

public interface Manager<T> {
    public void add() throws InfraException;
    public void remove() throws InfraException;
    public Map<String, T> list() throws InfraException;
    public void restore(Memento<?> memento) throws InfraException;
    public Memento<T> save() throws InfraException;
}