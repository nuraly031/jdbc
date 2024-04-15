package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public static SessionFactory sessionFactory;

    static {
        sessionFactory = Util.sessionFactory();
    }


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction =session.beginTransaction();

        try {
            String sql = "CREATE TABLE IF NOT EXISTS userss\n" +
                    "(\n"+
                    "ID BIGSERIAL\n" +
                    "PRIMARY KEY,\n" +
                    "AGE SMALLINT,\n" +
                    "LASTNAME VARCHAR(255),\n" +
                    "NAME VARCHAR(255)\n" +
                    ");";

            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try {
            String sql = "DROP TABLE IF EXISTS userss";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try {
            User user = new User(name,lastName,age);
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try {
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users;
        try {
            users =session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
}