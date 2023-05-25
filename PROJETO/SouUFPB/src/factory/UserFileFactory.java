package factory;

import infra.UserFile;

public class UserFileFactory implements FileFactory {
    public CustomFile create() {
        return new UserFile();
    }
}