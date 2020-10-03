package com.dbs.hibernate.compositecolumn.seperate;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private String id;

    private String name;

    private Set<Contact> contacts = new HashSet<Contact>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", contacts="
                + contacts + "]";
    }
}
