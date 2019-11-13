package ru.kupchenkov.dao;

import ru.kupchenkov.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserDao {

    private EntityManager manager;

    public UserDao(EntityManager manager) {
        this.manager = manager;
    }

    public void save(User user) {
        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            throw e;
        }
    }

    public User findById(int id) {
        return manager.find(User.class, id);
    }

    public User findByLoginPassword(String login, String password) {
        try {
            return manager.createQuery("from User where login = :login and password = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }

    }
}
