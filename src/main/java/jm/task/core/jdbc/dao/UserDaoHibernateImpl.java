package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user(\n" +
                    "  id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "  name VARCHAR(30),\n" +
                    "  lastName VARCHAR(30),\n" +
                    "  age INT\n" +
                    "  );").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List list_user;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            list_user = session.createCriteria(User.class).list();
            transaction.commit();
            return list_user;
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            transaction.commit();
        } catch (Exception exp) {
            System.out.println("An error has occurred");
            exp.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
