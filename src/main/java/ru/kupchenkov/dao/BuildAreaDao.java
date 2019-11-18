package ru.kupchenkov.dao;

import ru.kupchenkov.entity.BuildArea;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class BuildAreaDao {

    private EntityManager manager;

    public BuildAreaDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(BuildArea buildArea) {
        manager.persist(buildArea);
    }

    public BuildArea findByArea(double area) {
        try {
            return manager.createQuery("from BuildArea where :area between startValue and endValue", BuildArea.class)
                    .setParameter("area", area).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
