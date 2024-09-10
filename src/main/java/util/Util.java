package util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "postgres";

    private static Connection connection;
        public static Connection getConnection(){
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                connection.setAutoCommit(false);
            } catch (SQLException e) {
                System.out.println("Ошибка при подключении к базе данных:" + e.getMessage());
            }
            return connection;
        }
}
