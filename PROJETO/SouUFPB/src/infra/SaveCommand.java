package infra;

import java.util.Map;

/**
 * SaveCommand
 */
public interface SaveCommand<T> {
    public void execute(Map<String,T> data);
}