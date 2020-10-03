package com.dbs.hibernate.mapping.one2many;

import java.util.Set;

/*
 * 
 create table CUSTOMERS (
 ID bigint not null ,
 NAME varchar(15),
 primary key (ID)
 );
 
 create table ORDERS (
 ID bigint not null ,
 ORDER_NUMBER varchar(15),
 CUSTOMER_ID bigint not null,
 primary key (ID)
 );
 
 alter table ORDERS add index IDX_CUSTOMER_ID (CUSTOMER_ID),
 add constraint FK_CUSTOMER_ID foreign key (CUSTOMER_ID) references CUSTOMERS (ID);

 */
public class Customer {

    private Long id;

    private String name;

    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + "]";
    }
}
