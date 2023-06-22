package infra;

import java.util.Map;

import business.control.Strategy;

public interface Memento{
    public Map<String,?> getState();
    public Strategy<?> getOriginator();
}