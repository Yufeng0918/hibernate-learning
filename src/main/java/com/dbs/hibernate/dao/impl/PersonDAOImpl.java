package com.dbs.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbs.hibernate.dao.PersonDAO;
import com.dbs.hibernate.model.Person;
import com.dbs.hibernate.util.HibernateUtil;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public void savePerson(Person person) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(person);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listAllPersons() {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        List<Person> list = null;

        try {
            Query query = session.createQuery("from Person");
            list = (List<Person>) query.list();
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return list;
    }

    @Override
    public void removePerson(String id) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Person person = (Person) session.get(Person.class, Integer.valueOf(id));
            session.delete(person);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Override
    public Person getSinglePersonById(String id) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        Person person = null;

        try {
            person = (Person) session.get(Person.class, Integer.valueOf(id));
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }

        return person;
    }

    @Override
    public void updatePerson(Person person) {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.update(person);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }
}
