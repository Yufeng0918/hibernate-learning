package com.dbs.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.inherit.single.Person;
import com.dbs.hibernate.mapping.inherit.single.Student;
import com.dbs.hibernate.mapping.inherit.single.Teacher;
import com.dbs.hibernate.util.HibernateUtil;

public class S11_InheritTest2 {

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

            Query query = session.createQuery("from Person");

            @SuppressWarnings("unchecked")
            List<Person> list = query.list();

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof Student) {
                    System.out.println(((Student) list.get(i)).toString());
                }

                if (list.get(i) instanceof Teacher) {
                    System.out.println(((Teacher) list.get(i)).toString());
                }
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
