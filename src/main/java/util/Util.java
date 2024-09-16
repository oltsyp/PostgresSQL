package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.logging.Logger;

public class Util {

    private static SessionFactory sessionFactory;
    private static Logger logger;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                // Создание конфигурации
                Configuration configuration = new Configuration();

                // Добавление аннотированных классов
                configuration.addAnnotatedClass(User.class);

                // Построение реестра служб
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                ServiceRegistry serviceRegistry = registryBuilder.build();

                // Построение SessionFactory
                Metadata metadata = new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(User.class)
                        .buildMetadata();

                sessionFactory = metadata.buildSessionFactory();

            } catch (Exception e) {
                logger.info("Ошибка при создании SessionFactory: ");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}



//    private static SessionFactory sessionFactory;
//
//    public Util() {
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration().configure();
//                configuration.addAnnotatedClass(User.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());
//
//            } catch (Exception e) {
//                System.out.println("Исключение!" + e);
//            }
//        }
//        return sessionFactory;
//    }


//    private static SessionFactory sessionFactory;
//
//    static {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        getSessionFactory().close();
//    }

//1
//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USERNAME = "java";
//    private static final String PASSWORD = "postgres";
//
//    private static Connection connection;
//        public static Connection getConnection(){
//            try {
//                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
////                connection.setAutoCommit(false);
//            } catch (SQLException e) {
//                System.out.println("Ошибка при подключении к базе данных:" + e.getMessage());
//            }
//            return connection;
//        }

