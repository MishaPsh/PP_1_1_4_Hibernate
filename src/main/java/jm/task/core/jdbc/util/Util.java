package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "Rwtestf12358.";
    private static final String CURREBT_SESSION_CONTEXT_CLASS = "thread";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String SHOW_SQL = "true";


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties hibernateConnectionProperties = new Properties();
            hibernateConnectionProperties.put(Environment.URL, URL);
            hibernateConnectionProperties.put(Environment.DRIVER, DRIVER);
            hibernateConnectionProperties.put(Environment.USER, USER);
            hibernateConnectionProperties.put(Environment.PASS, PASS);
            hibernateConnectionProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURREBT_SESSION_CONTEXT_CLASS);
            hibernateConnectionProperties.put(Environment.DIALECT, DIALECT);
            hibernateConnectionProperties.put(Environment.SHOW_SQL, SHOW_SQL);

            Configuration configuration = new Configuration().setProperties(hibernateConnectionProperties)
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}

