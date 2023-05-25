package factory;

import infra.CursoFile;

public class CursoFileFactory implements FileFactory {
    public CustomFile create() {
        return new CursoFile();
    }
}
