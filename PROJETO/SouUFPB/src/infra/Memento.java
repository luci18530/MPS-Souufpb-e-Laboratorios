package infra;

import java.util.Map;

public interface Memento<T> {
    public Map<String,T> getState();
}