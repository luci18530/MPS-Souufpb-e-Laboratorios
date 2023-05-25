package factory;


import java.lang.System.Logger;
import java.util.Map;

import business.model.Curso;
import business.model.Questionario;
import business.model.User;
import infra.InfraException;

public interface CustomFile {
    Map<String, User> loadUsers() throws InfraException;
    void saveUsers(Map<String, User> users);
    java.util.logging.Logger getLogger();
    Map<String, Curso> carregarCursos() throws InfraException;
    void salvarCursos(Map<String, Curso> cursos);
    Map<String, Questionario> carregarQuestionarios() throws InfraException;
    void salvarQuestionarios(Map<String, Questionario> questionarios);
    
}