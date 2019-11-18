package ru.kupchenkov.additional;

import com.vaadin.server.VaadinSession;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AdditionalUtils {

    public static EntityManagerFactory getFactory(VaadinSession session) {
        return (EntityManagerFactory) session.getAttribute("factory");
    }

    public static boolean isInteger(String value) {
        try {
            int i = Integer.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static LocalDate dateToLocalDate(Date date) {
       return LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
    }
}
