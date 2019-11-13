package ru.kupchenkov.additional;

import com.vaadin.server.VaadinSession;

import javax.persistence.EntityManagerFactory;

public class AdditionalUtils {

    public static EntityManagerFactory getFactory(VaadinSession session) {
        return (EntityManagerFactory) session.getAttribute("factory");
    }

}
