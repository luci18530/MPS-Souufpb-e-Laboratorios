package business.control;

import java.util.Map;


import infra.InfraException;
import infra.Memento;

public interface Manager<T> {
    public void add(String[] args) throws InfraException;
    public void remove(String key) throws InfraException;
    public Map<String, T> list() throws InfraException;
    public void restore(Memento<?> memento) throws InfraException;
    public Memento<T> save() throws InfraException;
}
