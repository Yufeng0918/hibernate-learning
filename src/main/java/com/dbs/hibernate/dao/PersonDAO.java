package com.dbs.hibernate.dao;

import java.util.List;

import com.dbs.hibernate.model.Person;

public interface PersonDAO {

    public void savePerson(Person person);

    public List<Person> listAllPersons();

    public void removePerson(String id);

    public Person getSinglePersonById(String id);

    public void updatePerson(Person person);
}
