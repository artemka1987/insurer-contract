package ru.kupchenkov.additional;

import com.vaadin.server.VaadinSession;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static boolean isPositiveInteger(String value) {
        try {
            int i = Integer.valueOf(value);
            if (i > 0) return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        try {
            double d = Double.valueOf(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static LocalDate dateToLocalDate(Date date) {
       return LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
    }

    public static Date localDateToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static List<Integer> getListYearToCurent() {
        List<Integer> list = new ArrayList<>();
        int endYear = 1900;
        for (int i = LocalDate.now().getYear(); i >= endYear; i--) {
            list.add(i);
        }
        return list;
    }

    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static long yearsBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.YEARS.between(startDate, endDate);
    }
}
