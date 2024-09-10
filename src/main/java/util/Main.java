package util;

import dao.UserDao;
import dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {

        UserDao userdao = new UserDaoJDBCImpl();

        userdao.createUsersTable();

        userdao.saveUser("John", "Doe", (byte) 30);
        userdao.saveUser("Liam", "Smith", (byte) 25);
        userdao.saveUser("Bob", "Johnson", (byte) 35);
        userdao.saveUser("Eve", "Brown", (byte) 28);

        userdao.removeUserById(1);
        userdao.getAllUsers();
        userdao.cleanUsersTable();
        userdao.dropUsersTable();
    }
}
