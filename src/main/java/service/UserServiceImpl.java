package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import model.User;;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void removeUserById(long id) {
        userDao.removeUserById(1);
    }

    public void getAllUsers() {
        userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}