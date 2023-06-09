package factory;

import business.model.User;

public interface UserFactory {
    User createUser(String login, String email, String senha);
}
