package ru.kupchenkov.main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import ru.kupchenkov.dao.RealtyTypeDao;
import ru.kupchenkov.dao.UserDao;
import ru.kupchenkov.entity.RealtyType;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.view.LoginView;


@Theme("valo")
@StyleSheet("vaadin://css/contract.css")
@Title("Договор страховщика")
public class MainUI extends UI {

    private EntityManagerFactory factory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        factory = Persistence.createEntityManagerFactory("Mssql");
        VaadinSession.getCurrent().setAttribute("factory", factory);

        /////////////////////////////// Create test data ///////////////////////////////////////////////////////////////
        // Add user
        UserDao userDao = new UserDao(factory.createEntityManager());
        userDao.save(new User("Администратор", "admin", "admin"));
        // Add realty types
        RealtyTypeDao realtyTypeDao = new RealtyTypeDao(factory.createEntityManager());
        realtyTypeDao.save(new RealtyType("Квартира", 1.7f));
        realtyTypeDao.save(new RealtyType("Дом", 1.5f));
        realtyTypeDao.save(new RealtyType("Комната", 1.3f));

        new Navigator(this, this);
        getNavigator().addView(LoginView.NAME, LoginView.class);
        getNavigator().navigateTo(LoginView.NAME);

    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}
