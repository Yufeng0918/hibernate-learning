package com.dbs.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.dbs.hibernate.hql.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S14_QBCTest {

    @Test
    public void testBetween() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class).add(
                    Restrictions.between("age", new Integer(12),
                            new Integer(30)));

            @SuppressWarnings("unchecked")
            List<Student> list = criteria.list();

            for (Student student : list) {
                System.out.println(student.getName());
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testLike() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class).add(
                    Restrictions.like("name", "s%"));

            @SuppressWarnings("unchecked")
            List<Student> list = criteria.list();

            for (Student student : list) {
                System.out.println(student.getName());
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testIn() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class).add(
                    Restrictions.in("name", new String[]{"Student2",
                            "Student3"}));

            @SuppressWarnings("unchecked")
            List<Student> list = criteria.list();

            for (Student student : list) {
                System.out.println(student.getName());
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testOrder() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class)
                    .addOrder(Order.asc("age")).addOrder(Order.desc("cardId"));

            @SuppressWarnings("unchecked")
            List<Student> list = criteria.list();

            for (Student student : list) {
                System.out.println(student.getName());
            }

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

}
