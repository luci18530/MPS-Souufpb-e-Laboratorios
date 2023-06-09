package factory;

import business.model.User;

public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String login, String email, String senha) {
        return new User(login, email, senha);
    }
}
