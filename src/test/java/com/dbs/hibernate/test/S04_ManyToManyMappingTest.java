package com.dbs.hibernate.test;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.many2many.Course;
import com.dbs.hibernate.mapping.many2many.Student;
import com.dbs.hibernate.util.HibernateUtil;

public class S04_ManyToManyMappingTest {

    @Test
    public void testAddManyToMany() {
        Student student = new Student();
        student.setName("zhangsan");

        Course course = new Course();
        course.setName("math");

        student.setCourses(new HashSet<Course>());
        course.setStudents(new HashSet<Student>());

        student.getCourses().add(course);
        course.getStudents().add(student);

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
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
    public void testQueryManyToMany() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Student student = (Student) session.get(Student.class,
                    "4028811b4bce43c5014bce43c6440000");
            Course course = (Course) session.get(Course.class,
                    "4028811b4bce43c5014bce43c6550001");

            System.out.println(student.getName());
            System.out.println(course.getName());
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
