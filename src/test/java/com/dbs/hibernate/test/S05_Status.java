package com.dbs.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.recursive.Category;
import com.dbs.hibernate.util.HibernateUtil;

public class S05_Status {

    @Test
    public void testGet() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category category1 = (Category) session.get(Category.class,
                    new Long(1));
            Category category2 = (Category) session.get(Category.class,
                    new Long(1));
            System.out.println(category1.toString());
            System.out.println(category2.toString());
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testLoad() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category category1 = (Category) session.load(Category.class,
                    new Long(1));
            Category category2 = (Category) session.load(Category.class,
                    new Long(1));
            System.out.println(category1.toString());
            System.out.println(category2.toString());
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
