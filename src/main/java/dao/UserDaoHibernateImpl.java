package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;
import model.User;
import org.hibernate.Transaction;
import util.Util;



public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users (id SERIAL,  name TEXT, lastName TEXT, age INT, PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User").list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}