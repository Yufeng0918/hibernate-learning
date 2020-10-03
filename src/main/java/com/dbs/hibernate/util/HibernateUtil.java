package com.dbs.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // sessionFactory = new Configuration().configure()
            // .buildSessionFactory();

            Configuration cfg = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).buildServiceRegistry();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Session openSession() {
        Session session = sessionFactory.openSession();
        return session;
    }

    public static void close(Session session) {
        if (null != session) {
            session.close();
        }
    }

}
