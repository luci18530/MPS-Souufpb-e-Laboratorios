package factory;

import business.model.Questionario;

public class QuestionarioFactoryImpl implements QuestionarioFactory {
    @Override
    public Questionario createQuestionario(String pergunta, String area) {
        return new Questionario(pergunta, area);
    }
}
