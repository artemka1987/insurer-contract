package ru.kupchenkov.dao;

import ru.kupchenkov.entity.Person;

import javax.persistence.EntityManager;

public class PersonDao {

    private EntityManager manager;

    public PersonDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Person person) {
        manager.persist(person);
    }

}
