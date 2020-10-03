package com.dbs.hibernate.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.dbs.hibernate.mapping.one2many.Customer;
import com.dbs.hibernate.mapping.one2many.Order;
import com.dbs.hibernate.mapping.recursive.Category;
import com.dbs.hibernate.util.HibernateUtil;

public class S02_OneToManyMappingTest {

    @Test
    public void testAddOneToMany() {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Customer customer = new Customer();
            customer.setName("zhangsan");
            customer.setOrders(new HashSet<Order>());

            Order order1 = new Order();
            order1.setOrderNumber("order1");

            Order order2 = new Order();
            order2.setOrderNumber("order2");

            Order order3 = new Order();
            order3.setOrderNumber("order3");

            order1.setCustomer(customer);
            order2.setCustomer(customer);
            order3.setCustomer(customer);

            customer.getOrders().add(order1);
            customer.getOrders().add(order2);
            customer.getOrders().add(order3);
            session.save(customer);

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
    public void testQueryOneToMany() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        Customer customer = null;

        try {
            tx = session.beginTransaction();

            customer = (Customer) session.get(Customer.class, new Long(1));
            System.out.println(customer.getName());

            Set<Order> orders = customer.getOrders();
            System.out.println(orders);
            for (Order order : orders) {
                System.out.println(order.getOrderNumber());
            }

            tx.commit();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            if (null != tx) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void testDeleteOneToMany() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Customer customer = null;

        try {
            tx = session.beginTransaction();
            customer = (Customer) session.get(Customer.class, new Long(2));
            session.delete(customer);
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
    public void testAddOneToManyRecursive() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Category category1 = new Category("level1", null,
                    new HashSet<Category>());
            Category category2 = new Category("level2", null,
                    new HashSet<Category>());
            Category category3 = new Category("level2", null,
                    new HashSet<Category>());
            Category category4 = new Category("level3", null,
                    new HashSet<Category>());
            Category category5 = new Category("level3", null,
                    new HashSet<Category>());
            Category category6 = new Category("level3", null,
                    new HashSet<Category>());
            Category category7 = new Category("level3", null,
                    new HashSet<Category>());

            category2.setParentCategory(category1);
            category3.setParentCategory(category1);
            category1.getChildCategories().add(category2);
            category1.getChildCategories().add(category3);

            category4.setParentCategory(category2);
            category5.setParentCategory(category2);
            category2.getChildCategories().add(category4);
            category2.getChildCategories().add(category5);

            category6.setParentCategory(category3);
            category7.setParentCategory(category3);
            category3.getChildCategories().add(category6);
            category3.getChildCategories().add(category7);

            System.out.println(session.save(category1));
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
    public void testQueryOneToManyRecursive() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category category = (Category) session.load(Category.class,
                    new Long(3));
            // Category category_ = (Category) session.load(Category.class,
            // new Long(8));

            System.out.println(category.toString());
            // System.out.println(category_.getName());

            // System.out.println(category == category_);
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
    public void testDeleteOneToManyRecursive() {

        Session session = HibernateUtil.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category category = (Category) session.get(Category.class,
                    new Long(1));
            System.out.println(category.toString());
            session.delete(category);
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
