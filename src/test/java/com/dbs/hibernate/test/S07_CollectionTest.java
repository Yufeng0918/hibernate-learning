package com.dbs.hibernate.test;

//import java.util.Map;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.set.Student;
//import com.dbs.hibernate.mapping.bag.Student;
//import com.dbs.hibernate.mapping.bag.Team;
//import com.dbs.hibernate.mapping.list.Student;
//import com.dbs.hibernate.mapping.list.Team;
import com.dbs.hibernate.mapping.set.Team;
//import com.dbs.hibernate.mapping.map.Student;
//import com.dbs.hibernate.mapping.map.Team;
import com.dbs.hibernate.util.HibernateUtil;

public class S07_CollectionTest {

    @Test
    public void testAddMap() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            // Team team = new Team();
            // team.setTeamName("team1");
            // Map<String, Student> map = team.getStudents();
            // Student student1 = new Student();
            // student1.setAge(20);
            // student1.setName("zhangsan");
            // student1.setTeam(team);
            //
            // Student student2 = new Student();
            // student2.setAge(20);
            // student2.setName("zhangsan");
            // student2.setTeam(team);
            //
            // Student student3 = new Student();
            // student3.setAge(20);
            // student3.setName("zhangsan");
            // student3.setTeam(team);
            //
            // map.put("aaa", student1);
            // map.put("bbb", student2);
            // map.put("ccc", student3);
            // session.save(team);
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
    public void testQueryMap() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            // Team team = (Team) session.createQuery(
            // "from Team t where t.teamName = 'team1'").uniqueResult();
            //
            // Map<String, Student> map = team.getStudents();
            // Collection<Student> col = map.values();
            // Iterator<Student> iter = col.iterator();
            // while (iter.hasNext()) {
            // System.out.println(iter.next());
            // }

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
    public void testAddSet() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            // Team team = new Team();
            // team.setTeamName("team1");
            // team.getStudents().add("zhangsan");
            // team.getStudents().add("lisi");
            // team.getStudents().add("wangwu");
            //
            // session.save(team);

            Team team = new Team();
            team.setTeamName("team1");
            Set<Student> set = new TreeSet<Student>();
            Student student1 = new Student();
            student1.setAge(20);
            student1.setName("zhangsan");
            student1.setCardId("111");
            student1.setTeam(team);

            Student student2 = new Student();
            student2.setAge(20);
            student2.setName("zhangsan");
            student2.setCardId("222");
            student2.setTeam(team);

            Student student3 = new Student();
            student3.setAge(20);
            student3.setName("zhangsan");
            student3.setCardId("333");
            student3.setTeam(team);

            set.add(student1);
            set.add(student2);
            set.add(student3);
            session.save(team);
            session.save(student1);
            session.save(student2);
            session.save(student3);
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
    public void testQuerySet() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Team team = (Team) session.get(Team.class,
                    "4028811b4bcf460e014bcf460ee60000");

            Set<Student> set = team.getStudents();
            Iterator<Student> iter = set.iterator();

            while (iter.hasNext()) {
                System.out.println(iter.next());
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
    public void testAddList() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Team team1 = new Team();
            // team1.setTeamName("team1");
            //
            // Team team2 = new Team();
            // team2.setTeamName("team2");
            //
            // Student s1 = new Student();
            // Student s2 = new Student();
            // Student s3 = new Student();
            // Student s4 = new Student();
            // Student s5 = new Student();
            // Student s6 = new Student();
            //
            // s1.setName("zhangsan");
            // s2.setName("lisi");
            // s3.setName("wangwu");
            // s4.setName("zhaoliu");
            // s5.setName("hello");
            // s6.setName("world");
            //
            // team1.getStudents().add(s1);
            // team1.getStudents().add(s2);
            //
            // team2.getStudents().add(s3);
            // team2.getStudents().add(s4);
            // team2.getStudents().add(s5);
            // team2.getStudents().add(s6);
            //
            // session.save(team1);
            // session.save(team2);

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
    public void testAddBag() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Team team = new Team();
            // team.setTeamName("team1");
            //
            // Team team2 = new Team();
            // team2.setTeamName("team2");
            //
            // Student s1 = new Student();
            // Student s2 = new Student();
            // Student s3 = new Student();
            // Student s4 = new Student();
            // Student s5 = new Student();
            // Student s6 = new Student();
            //
            // s1.setName("zhangsan");
            // s2.setName("lisi");
            // s3.setName("wangwu");
            // s4.setName("zhaoliu");
            // s5.setName("hello");
            // s6.setName("world");
            //
            // s1.setTeam(team);
            // s2.setTeam(team);
            //
            // s3.setTeam(team2);
            // s4.setTeam(team2);
            // s5.setTeam(team2);
            // s6.setTeam(team2);
            //
            // team.getStudents().add(s1);
            // team.getStudents().add(s2);
            //
            // team2.getStudents().add(s3);
            // team2.getStudents().add(s4);
            // team2.getStudents().add(s5);
            // team2.getStudents().add(s6);
            //
            // session.save(team);
            // session.save(team2);

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
