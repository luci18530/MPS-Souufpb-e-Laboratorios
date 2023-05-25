package factory;

import infra.QuestionarioFile;

public class QuestionarioFileFactory implements FileFactory {
    public CustomFile create() {
        return new QuestionarioFile();
    }
}