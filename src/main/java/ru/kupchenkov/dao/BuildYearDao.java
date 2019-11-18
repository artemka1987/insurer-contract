package ru.kupchenkov.dao;

import ru.kupchenkov.entity.BuildYear;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class BuildYearDao {

    private EntityManager manager;

    public BuildYearDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(BuildYear buildYear) {
        manager.persist(buildYear);
    }

    public BuildYear findById(int id) {
        return manager.find(BuildYear.class, id);
    }

    public BuildYear findByYear(int year) {
        try {
            return manager.createQuery("from BuildYear where :year between startYear and endYear", BuildYear.class)
                    .setParameter("year", year)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
