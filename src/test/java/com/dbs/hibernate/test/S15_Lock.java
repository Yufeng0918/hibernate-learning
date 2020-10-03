package com.dbs.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.lock.version.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S15_Lock {

    @Test
    public void testAddLockVersion() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student student = new Student();

            student.setCardId("123456");
            student.setAge(40);
            student.setName("zhangsan");

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
    public void testQueryUpdateLockVersion() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Session session1 = HibernateUtil.openSession();
            Session session2 = HibernateUtil.openSession();

            Student student1 = (Student) session1
                    .createQuery("from Student s where s.name = :name")
                    .setString("name", "zhangsan").uniqueResult();

            Student student2 = (Student) session2
                    .createQuery("from Student s where s.name = :name")
                    .setString("name", "zhangsan").uniqueResult();

            System.out.println(student1.getVersion());
            System.out.println(student2.getVersion());

            Transaction tx1 = session1.beginTransaction();
            student1.setName("lisi");
            tx1.commit();

            System.out.println(student1.getVersion());
            System.out.println(student2.getVersion());

            Transaction tx2 = session2.beginTransaction();
            student2.setName("wangwu");
            tx2.commit();

            session1.close();
            session2.close();
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
    public void testAddLockTimestamp() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            com.dbs.hibernate.lock.timestamp.Student student = new com.dbs.hibernate.lock.timestamp.Student();

            student.setName("zhangsan");
            student.setCardId("123456");
            student.setAge(30);

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
