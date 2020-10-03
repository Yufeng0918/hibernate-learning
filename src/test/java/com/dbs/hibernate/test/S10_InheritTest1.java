package com.dbs.hibernate.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.inherit.seperate.Person;
import com.dbs.hibernate.mapping.inherit.seperate.Student;
import com.dbs.hibernate.mapping.inherit.seperate.Teacher;
import com.dbs.hibernate.util.HibernateUtil;

public class S10_InheritTest1 {

    @Test
    public void testAddPerson() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student student = new Student();
            student.setCardId("123456");
            student.setName("zhangsan");

            Teacher teacher = new Teacher();
            teacher.setSalary(100);
            teacher.setName("lisi");

            session.save(student);
            session.save(teacher);
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
    public void testQueryPerson() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from com.dbs.hibernate.mapping.inherit.seperate.Person");

            @SuppressWarnings("unchecked")
            Iterator<Person> iter = query.iterate();

            while (iter.hasNext()) {
                Person p = (Person) iter.next();
                System.out.println(p.toString());
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
