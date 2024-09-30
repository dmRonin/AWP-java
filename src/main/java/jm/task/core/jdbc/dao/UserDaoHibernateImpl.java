package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
        sessionFactory = Util.openHibernate();
    }
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS public.users " +
            "(id BIGSERIAL PRIMARY KEY, " +
            "name VARCHAR(50) NOT NULL, " +
            "last_name VARCHAR(50) NOT NULL, " +
            "age SMALLINT NOT NULL);";
    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS users;";
    public static final String TRUNCATE_TABLE = "TRUNCATE TABLE users;";

    private final SessionFactory sessionFactory;


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(DELETE_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User users = User.builder()
                    .name(name)
                    .lastName(lastName)
                    .age(age)
                    .build();

            session.save(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            session.getTransaction().commit();
            users.forEach(System.out::println);

            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(TRUNCATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
