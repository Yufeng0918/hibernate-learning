package com.dbs.hibernate.service;

import java.util.List;

import com.dbs.hibernate.model.Person;


public interface PersonService {

    public void savePerson(Person person);

    public List<Person> listAllPersons();

    public void removePerson(String id);

    public Person getSinglePersonById(String id);

    public void updatePerson(Person person);
}
