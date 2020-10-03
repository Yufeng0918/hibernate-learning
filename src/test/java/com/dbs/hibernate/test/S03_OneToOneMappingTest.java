package com.dbs.hibernate.test;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.one2one.team.IdCard;
import com.dbs.hibernate.mapping.one2one.team.Student;
//import com.dbs.hibernate.mapping.one2one.foreignkey.IdCard;
//import com.dbs.hibernate.mapping.one2one.foreignkey.Student;
import com.dbs.hibernate.mapping.one2one.team.Team;
//import com.dbs.hibernate.mapping.one2one.primarykey.IdCard;
//import com.dbs.hibernate.mapping.one2one.primarykey.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S03_OneToOneMappingTest {

    @Test
    public void testAddOneToOne() {
        Student student = new Student();
        student.setName("zhangsan");

        IdCard idCard = new IdCard();
        idCard.setNumber(987654);

        student.setIdCard(idCard);
        idCard.setStudent(student);

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testLoadOneToOne() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Student student = null;
        IdCard idCard = null;

        try {
            tx = session.beginTransaction();
            student = (Student) session.load(Student.class,
                    "4028811b4bce10fc014bce10fd800000");
            System.out.println(student.getIdCard());

            idCard = (IdCard) session.load(IdCard.class,
                    "4028811b4bce10fc014bce10fd8c0001");
            System.out.println(idCard.toString());
            System.out.println(idCard.getStudent().getId());

            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testAddTeam() {
        Student student = new Student();
        student.setName("zhangsan");

        IdCard idCard = new IdCard();
        idCard.setNumber(987654);

        student.setIdCard(idCard);
        idCard.setStudent(student);

        Team team = new Team();
        team.setName("team1");

        team.setStudents(new HashSet<Student>());
        team.getStudents().add(student);

        student.setTeam(team);

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(team);
            tx.commit();
        } catch (Exception ex) {
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }


    @Test
    public void testQueryTeam() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Team t = (Team) session.get(Team.class, "4028811b4bce2acf014bce2ad0a10000");
            System.out.println(t.getName());

            Student s = (Student) session.get(Student.class, "4028811b4bce2acf014bce2ad0b20001");
            System.out.println(s.getName());
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
