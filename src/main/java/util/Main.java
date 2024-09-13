package util;

import dao.UserDao;
import dao.UserDaoHibernateImpl;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernateImpl();

        /* create new user */
          userDao.createUsersTable();

        User user1 = new User("John", "Doe", (byte) 30);
        User user2 = new User("Liam", "Smith", (byte) 25);
        User user3 = new User("Bob", "Johnson", (byte) 35);
        User user4 = new User("Eve", "Brown", (byte) 28);

        userDao.saveUser(user1);
        userDao.saveUser(user2);
        userDao.saveUser(user3);
        userDao.saveUser(user4);


        userDao.removeUserById(1);
        userDao.getAllUsers();

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}


//        User user = new User();
//        user.setName("user01");
//        user.setLastName("fname01");
//        user.setAge(Byte.valueOf("1"));
//
//        userDao.saveUser(user);



//public class Main {
//
//    public static void main(String[] args) throws SQLException {
//
//        UserService userService = new UserService();
//        User user = new User("Masha",26);
//        userService.saveUser(user);
//    }
//}

//1
//    public static void main(String[] args) {
//
//        UserDao userdao = new UserDaoJDBCImpl();
//
//        userdao.createUsersTable();
//
//        userdao.saveUser("John", "Doe", (byte) 30);
//        userdao.saveUser("Liam", "Smith", (byte) 25);
//        userdao.saveUser("Bob", "Johnson", (byte) 35);
//        userdao.saveUser("Eve", "Brown", (byte) 28);
//
//        userdao.removeUserById(1);
//        userdao.getAllUsers();
//        userdao.cleanUsersTable();
//        userdao.dropUsersTable();
//    }

