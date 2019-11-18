package ru.kupchenkov.dao;

import ru.kupchenkov.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonDao {

    private EntityManager manager;

    public PersonDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Person person) {
        manager.persist(person);
    }

    public List<Person> findPersonsFromLikeFio(String fio) {
        return manager.createQuery("from Person where upper(concat(lastName, ' ', firstName, ' ', middleName)) like :fio", Person.class)
                .setParameter("fio", fio.trim().toUpperCase().replaceAll(" ","%") + "%").getResultList();
    }

}
