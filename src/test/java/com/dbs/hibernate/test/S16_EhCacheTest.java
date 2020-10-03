package com.dbs.hibernate.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.cache.ehcache.Student;
import com.dbs.hibernate.cache.ehcache.Team;
import com.dbs.hibernate.util.HibernateUtil;

public class S16_EhCacheTest {

    @Test
    public void testAddStudent() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Team team = new Team();
            team.setTeamName("team2");

            for (int i = 0; i < 1200; i++) {
                Student student = new Student();

                student.setAge(30);
                student.setCardId("123456");
                student.setName("student" + i);
                student.setTeam(team);

                team.getStudents().add(student);
            }

            session.save(team);
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
    public void testQueryStudent() {

        Session session = HibernateUtil.openSession();
        try {
            Session session1 = HibernateUtil.openSession();
            Transaction tx1 = session1.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Student> list = session.createQuery(
                    "from Student s order by s.name asc").list();

            for (Student student : list) {
                System.out.println(student.getName());
            }

            tx1.commit();

            System.out.println("------------------------------");

            Session session2 = HibernateUtil.openSession();
            Transaction tx2 = session2.beginTransaction();

            Student s1 = (Student) session2.get(Student.class,
                    "4028811b4bd4e925014bd4e926380001");
            Student s2 = (Student) session2.get(Student.class,
                    "4028811b4bd4e925014bd4e926390004");

            System.out.println(s1.getName());
            System.out.println(s2.getName());

            tx2.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
