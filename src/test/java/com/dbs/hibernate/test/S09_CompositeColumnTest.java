package com.dbs.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.compositecolumn.seperate.Contact;
import com.dbs.hibernate.compositecolumn.seperate.Student;
//import com.dbs.hibernate.compositecolumn.single.Address;
//import com.dbs.hibernate.compositecolumn.single.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S09_CompositeColumnTest {

    @Test
    public void testAddAddess() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Student student = new Student();
            // Address address = new Address();
            // address.setHomeAddress("beijing");
            // address.setSchoolAddress("shanghai");
            // student.setName("zhangsan");
            // student.setAddress(address);
            // session.saveOrUpdate(student);

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
    public void testQueryAddess() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Student student = (Student) session.get(Student.class,
            // "4028811b4bcf6a6b014bcf6a6bc70000");
            // System.out.println(student.toString());

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
    public void testAddContact() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student student = new Student();
            student.setName("zhangsan");

            Contact contact1 = new Contact();
            contact1.setMethod("telphone");
            contact1.setAddress("123456");

            Contact contact2 = new Contact();
            contact2.setMethod("address");
            contact2.setAddress("beijing");

            student.getContacts().add(contact1);
            student.getContacts().add(contact2);

            session.save(student);
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
