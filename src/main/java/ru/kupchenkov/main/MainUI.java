package ru.kupchenkov.main;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityManager;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.apache.commons.codec.digest.DigestUtils;
import ru.kupchenkov.dao.BuildYearDao;
import ru.kupchenkov.dao.RealtyTypeDao;
import ru.kupchenkov.dao.UserDao;
import ru.kupchenkov.entity.BuildYear;
import ru.kupchenkov.entity.RealtyType;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.security.DesEncrypter;
import ru.kupchenkov.view.LoginView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;


@Theme("valo")
@StyleSheet("vaadin://css/contract.css")
@Title("Договор страховщика")
public class MainUI extends UI {

    private EntityManagerFactory factory;
    private EntityManager manager;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        factory = Persistence.createEntityManagerFactory("Postgres");
        VaadinSession.getCurrent().setAttribute("factory", factory);
        manager = factory.createEntityManager();

        manager.getTransaction().begin();
        try {
            /////////////////////////////// Create test data ///////////////////////////////////////////////////////////////
            // Add user
            UserDao userDao = new UserDao(manager);
            userDao.save(new User("Администратор", "admin", new DesEncrypter().encrypt("admin")));
            // Add realty types
            RealtyTypeDao realtyTypeDao = new RealtyTypeDao(manager);
            realtyTypeDao.save(new RealtyType("Квартира", 1.7d));
            realtyTypeDao.save(new RealtyType("Дом", 1.5d));
            realtyTypeDao.save(new RealtyType("Комната", 1.3d));
            // Add build year
            BuildYearDao buildYearDao = new BuildYearDao(manager);
            buildYearDao.save(new BuildYear(1900, 1999, 1.3d));
            buildYearDao.save(new BuildYear(2000, 2014, 1.6d));
            buildYearDao.save(new BuildYear(2015, LocalDate.now().getYear(), 2d));
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            Notification.show("Error: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }

        new Navigator(this, this);
        getNavigator().addView(LoginView.NAME, LoginView.class);
        getNavigator().navigateTo(LoginView.NAME);

    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}
