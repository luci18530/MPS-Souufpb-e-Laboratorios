package factory;

import business.model.Questionario;

public interface QuestionarioFactory {
    Questionario createQuestionario(String pergunta, String area);
}
