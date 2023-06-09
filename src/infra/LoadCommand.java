package infra;

import java.util.Map;

/**
 * FileCommand
 */
public interface LoadCommand<T> {
    public Map<String,T> execute() throws InfraException;    
}