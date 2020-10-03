package com.dbs.hibernate.service.impl;

import java.util.List;

import com.dbs.hibernate.dao.PersonDAO;
import com.dbs.hibernate.dao.impl.PersonDAOImpl;
import com.dbs.hibernate.model.Person;
import com.dbs.hibernate.service.PersonService;

public class PersonServiceImpl implements PersonService {

    public void savePerson(Person person) {
        PersonDAO personDAO = new PersonDAOImpl();
        personDAO.savePerson(person);
    }

    @Override
    public List<Person> listAllPersons() {
        PersonDAO personDAO = new PersonDAOImpl();
        return personDAO.listAllPersons();
    }

    @Override
    public void removePerson(String id) {
        PersonDAO personDAO = new PersonDAOImpl();
        personDAO.removePerson(id);
    }

    @Override
    public Person getSinglePersonById(String id) {
        PersonDAO personDAO = new PersonDAOImpl();
        return personDAO.getSinglePersonById(id);
    }

    @Override
    public void updatePerson(Person person) {
        PersonDAO personDAO = new PersonDAOImpl();
        personDAO.updatePerson(person);
    }

}
