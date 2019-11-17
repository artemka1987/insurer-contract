package ru.kupchenkov.dao;

import ru.kupchenkov.entity.RealtyType;

import javax.persistence.EntityManager;
import java.util.List;

public class RealtyTypeDao {

    private EntityManager manager;

    public RealtyTypeDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(RealtyType realtyType) {
        manager.persist(realtyType);
    }

    public List<RealtyType> getAll() {
        return manager.createQuery("from RealtyType order by name").getResultList();
    }

}
