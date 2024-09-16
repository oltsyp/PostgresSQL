package service;

import model.User;

public interface UserService {

    void createUsersTable();

    void dropUsersTable();

//    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    void getAllUsers();

    void cleanUsersTable();

    void saveUser(User user);
}
