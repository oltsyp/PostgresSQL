package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    Connection connection = Util.getConnection();

    private static final String create_new_table = "CREATE TABLE IF NOT EXISTS users (id SERIAL,  name TEXT, lastName TEXT, age INT, PRIMARY KEY (id))";
    private static final String drop_new_table = "DROP TABLE IF EXISTS users";
    private static final String clean_new_table = "TRUNCATE TABLE users";

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(create_new_table);
//            create_new_table.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
//                    "(id SERIAL,  name TEXT, lastName TEXT, age INT, PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
//        String drop = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(drop_new_table);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?, ?, ?);")) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setInt(3, age);
            pst.executeUpdate();
            System.out.println("Пользователь: " + name + " " + lastName + " возраст: " + age + " " + "добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Long userId = Long.valueOf(1);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
//            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
//        String clean = "TRUNCATE TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(clean_new_table);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    e.printStackTrace();
                }
            }
        }
    }
}