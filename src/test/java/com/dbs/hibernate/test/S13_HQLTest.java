package com.dbs.hibernate.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.hql.Course;
import com.dbs.hibernate.hql.Student;
import com.dbs.hibernate.hql.Team;
import com.dbs.hibernate.util.HibernateUtil;

public class S13_HQLTest {

    @Test
    public void testAddData() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Team team1 = new Team();
            Team team2 = new Team();
            Team team3 = new Team();

            team1.setTeamName("TEAM1");
            team2.setTeamName("TEAM2");
            team3.setTeamName("TEAM3");

            Student s1 = new Student();
            s1.setCardId("A1");
            s1.setName("Student1");
            s1.setAge(20);

            Student s2 = new Student();
            s2.setCardId("A2");
            s2.setName("Student2");
            s2.setAge(21);

            Student s3 = new Student();
            s3.setCardId("A3");
            s3.setName("Student3");
            s3.setAge(22);

            Set<Student> set1 = new HashSet<Student>();
            set1.add(s1);
            set1.add(s2);
            team1.setStudents(set1);

            Set<Student> set2 = new HashSet<Student>();
            set2.add(s3);
            team2.setStudents(set2);

            Course c1 = new Course();
            c1.setName("History");

            Course c2 = new Course();
            c2.setName("Math");

            Course c3 = new Course();
            c3.setName("English");

            Course c4 = new Course();
            c4.setName("Chinese");

            Set<Course> sc1 = new HashSet<Course>();
            sc1.add(c1);
            sc1.add(c2);
            sc1.add(c4);
            s1.setCourses(sc1);

            Set<Course> sc2 = new HashSet<Course>();
            sc2.add(c2);
            sc2.add(c3);
            s2.setCourses(sc2);

            Set<Course> sc3 = new HashSet<Course>();
            sc3.add(c1);
            s3.setCourses(sc3);

            session.save(team1);
            session.save(team2);
            session.save(team3);

            session.save(c1);
            session.save(c2);
            session.save(c3);
            session.save(c4);

            session.save(s1);
            session.save(s2);
            session.save(s3);
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
    public void testQueryByRow() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session
                    .createQuery("select s.name, s.age from Student s");

            @SuppressWarnings("unchecked")
            List<Object> list = query.list();

            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                System.out.println(obj[0] + ", " + obj[1]);
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
    public void testQueryByObject() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session
                    .createQuery("select new Student(s.name, s.age) from Student s");

            @SuppressWarnings("unchecked")
            List<Object> list = query.list();

            for (int i = 0; i < list.size(); i++) {
                Student student = (Student) list.get(i);
                System.out.println(student.toString());
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

    @SuppressWarnings("unchecked")
    @Test
    public void testQueryJoin() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        List<Object> list = new ArrayList<Object>();

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Team t join t.students");
            list = query.list();

            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);

            Team team = (Team) obj[0];
            Student student = (Student) obj[1];

            System.out.println(team.getTeamName());
            System.out.println(student.getName());
            System.out.println("----------");
        }

    }

    @Test
    public void testQuerySetEntity() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Team team = (Team) session.get(Team.class,
                    "4028811b4bd0d971014bd0d9728a0000");
            Query query = session
                    .createQuery("from Student s where s.team = :team and s.age > 20");
            query.setParameter("team", team);
            // query.setEntity("team", team);

            @SuppressWarnings("unchecked")
            List<Student> list = query.list();

            for (Student student : list) {
                System.out.println(student.toString());
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
    public void testQueryWithFitler() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Team team = (Team) session.get(Team.class,
                    "4028811b4bd0d971014bd0d9728a0000");
            Query query = session.createFilter(team.getStudents(),
                    "where age > 20");

            @SuppressWarnings("unchecked")
            List<Student> list = query.list();
            System.out.println(list.size());
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
