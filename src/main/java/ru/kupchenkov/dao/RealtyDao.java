package ru.kupchenkov.dao;

import ru.kupchenkov.entity.Realty;

import javax.persistence.EntityManager;

public class RealtyDao {

    private EntityManager manager;

    public RealtyDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Realty realty) {
        manager.persist(realty);
    }

}
