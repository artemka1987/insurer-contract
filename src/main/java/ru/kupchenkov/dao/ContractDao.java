package ru.kupchenkov.dao;

import ru.kupchenkov.entity.Contract;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ContractDao {

    private EntityManager manager;

    public ContractDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Contract contract) {
        manager.persist(contract);
    }

    public Contract findById(int id) {
        return manager.find(Contract.class, id);
    }

    public Contract findByNumber(int number) {
        try {
            return manager.createQuery("from Contract where number = :number", Contract.class).setParameter("number", number).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Contract> findByInsurerFio(String fio) {
        manager.getEntityManagerFactory().getCache().evictAll();
        return manager.createQuery("from Contract where upper(concat(person.lastName, ' ', person.firstName, ' ', person.middleName)) like :fio", Contract.class)
                .setParameter("fio", fio.trim().toUpperCase().replaceAll(" ","%") + "%").getResultList();
    }

    public List<Contract> getAll() {
        return manager.createQuery("from Contract ").getResultList();
    }
}
