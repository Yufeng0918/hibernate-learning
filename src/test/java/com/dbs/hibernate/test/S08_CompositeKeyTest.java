package com.dbs.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.compositekey.keyobject.Student;
import com.dbs.hibernate.compositekey.keyobject.StudentPrimaryKey;
//import com.dbs.hibernate.compositekey.serialize.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S08_CompositeKeyTest {

    @Test
    public void testAddSerialize() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Student student = new Student();
            //
            // student.setName("zhangsan");
            // student.setCardId("123456");
            // student.setAge(30);
            //
            // session.save(student);
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
    public void testQuerySerialize() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            // Student studentPrimaryKey = new Student();
            //
            // studentPrimaryKey.setCardId("123456");
            // studentPrimaryKey.setName("zhangsan");
            // Student student = (Student) session.get(Student.class,
            // studentPrimaryKey);
            // System.out.println(student.toString());
            // System.out.println(studentPrimaryKey.toString());
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
    public void testAddKeyObject() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            StudentPrimaryKey studentPrimaryKey = new StudentPrimaryKey();
            studentPrimaryKey.setCardId("123456");
            studentPrimaryKey.setName("lisi");

            Student student = new Student();
            student.setAge(30);
            student.setStudentPrimaryKey(studentPrimaryKey);

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

    @Test
    public void testQueryKeyObject() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            StudentPrimaryKey studentPrimaryKey = new StudentPrimaryKey();

            studentPrimaryKey.setCardId("123456");
            studentPrimaryKey.setName("lisi");

            Student student = (Student) session.get(Student.class,
                    studentPrimaryKey);

            System.out.println(student.toString());
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
