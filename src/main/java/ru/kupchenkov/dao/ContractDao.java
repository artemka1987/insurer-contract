package ru.kupchenkov.dao;

import ru.kupchenkov.entity.Contract;

import javax.persistence.EntityManager;

public class ContractDao {

    private EntityManager manager;

    public ContractDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Contract contract) {
        manager.persist(contract);
    }
}
