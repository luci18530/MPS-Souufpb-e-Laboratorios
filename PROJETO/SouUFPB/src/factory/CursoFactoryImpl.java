package factory;

import business.model.Curso;

public class CursoFactoryImpl implements CursoFactory {

    @Override
    public Curso createCurso(String nome, String cidade, String centro) {
        return new Curso(nome, cidade, centro);
    }
}
