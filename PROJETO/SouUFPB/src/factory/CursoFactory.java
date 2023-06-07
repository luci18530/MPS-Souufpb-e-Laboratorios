package factory;

import business.model.Curso;

public interface CursoFactory {
    Curso createCurso(String nome, String cidade, String centro, String args);
}
