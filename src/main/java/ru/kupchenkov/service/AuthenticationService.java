package ru.kupchenkov.service;

import com.vaadin.server.VaadinSession;
import ru.kupchenkov.additional.AdditionalUtils;
import ru.kupchenkov.dao.UserDao;
import ru.kupchenkov.entity.User;

public class AuthenticationService {

    public User authenticate(String login, String password) {
        UserDao userDao = new UserDao(AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager());
        return userDao.findByLoginPassword(login, password);
    }

}
