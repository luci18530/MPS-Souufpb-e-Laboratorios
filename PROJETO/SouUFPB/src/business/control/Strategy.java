package business.control;

import java.util.Map;

import infra.InfraException;
import infra.Memento;

public interface Strategy <T>{
    public void add() throws InfraException;
    public void remove() throws InfraException;
    public Map<String, T> list() throws InfraException;
    public void restore(Memento popped) throws InfraException;
    public Memento save() throws InfraException;
}