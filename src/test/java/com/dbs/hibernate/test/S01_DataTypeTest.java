package com.dbs.hibernate.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.model.People;
import com.dbs.hibernate.util.HibernateUtil;

public class S01_DataTypeTest {

    @Test
    public void testAddPeople() throws IOException {
        People people = new People();

        people.setUsername("lisi");
        people.setPassword("123456");
        people.setGender('F');
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        System.out.println(date);
        people.setBirthday(date);
        people.setGraduation(true);
        people.setTelphone(987654);
        people.setMarryTime(new Timestamp(new java.util.Date().getTime()));

        InputStream is = new FileInputStream(
                "D:/Workspace/IDEA/spring-struts-hibernate/hibernate/src/main/resources/@Hibernate_1.pdf");
        int length = is.available();
        System.out.println(is);

        byte[] buffer = new byte[length];

        is.read(buffer);

        people.setFile(buffer);

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(people);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDeletePeopleByList() {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        List<People> list;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from People");
            list = (List<People>) query.list();
            for (Iterator<People> iter = list.iterator(); iter.hasNext(); ) {
                session.delete(iter.next());
            }

            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        // while (iter.hasNext()) {
        // System.out.println(iter.next().getId());
        // }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDeletePeopleByIterator() {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from People");
            Iterator<People> iter = (Iterator<People>) query.iterate();
            while (iter.hasNext()) {
                session.delete(iter.next());
            }
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        // while (iter.hasNext()) {
        // System.out.println(iter.next().getId());
        // }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryPeople() {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        List<People> list = null;

        try {
            tx = session.beginTransaction();
            Query query = session
                    .createQuery("from People as p order by p.username")
                    .setFirstResult(2).setMaxResults(20);
            ;
            list = (List<People>) query.list();

            for (Iterator<People> iter = list.iterator(); iter.hasNext(); ) {
                People people = iter.next();
                System.out.println(people.toString());

                byte[] buffer = people.getFile();
                OutputStream os = new FileOutputStream("D:/" + people.getId()
                        + ".pdf");
                os.write(buffer);
                os.close();
            }

            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
