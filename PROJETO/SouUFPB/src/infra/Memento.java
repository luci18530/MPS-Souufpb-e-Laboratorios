package infra;

import java.util.Map;

import business.control.Manager;

public interface Memento{
    public Map<String,?> getState();
    public Manager<?> getOriginator();
}