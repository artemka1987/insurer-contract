package ru.kupchenkov.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.additional.OnEnterKeyHandler;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.resource.Images;
import ru.kupchenkov.service.AuthenticationService;
import ru.kupchenkov.view.user.view.UserView;

public class LoginView extends VerticalLayout implements View {

    public static final String NAME = "login";
    private FormLayout flayout = new FormLayout();
    private PasswordField password = new PasswordField("Пароль");
    private TextField tfUserName = new TextField("Логин");
    private Label resultLabel = new Label();
    private User user;

    public LoginView() {
        setSizeFull();
        resultLabel.addStyleName("errorlable");

        /// Top banner
        HorizontalLayout hlTop = new HorizontalLayout();
            hlTop.setWidth(100, Unit.PERCENTAGE);
        Label labelBanner = new Label("Договор страховщика");
            labelBanner.setWidthUndefined();
            labelBanner.addStyleName("lable-title-bold");

        /// Form authentication
        Panel panel = new Panel("Авторизация пользователя");
            panel.setSizeUndefined();
            panel.addStyleName("form-title");
            flayout.setSizeUndefined();
            flayout.setMargin(true);
            tfUserName.focus();
            tfUserName.setIcon(Images.icoUser);
            tfUserName.focus();
            tfUserName.addStyleName("lable-login-caption");
            password.setIcon(Images.icoPassword);
            password.addStyleName("lable-login-caption");
            Button btnLogin = new Button("Войти", Images.icoUnlock);
                btnLogin.setStyleName(ValoTheme.BUTTON_PRIMARY);
                btnLogin.setWidth(100, Unit.PERCENTAGE);
                btnLogin.addClickListener(loginClick);

        OnEnterKeyHandler onEnterHandler=new OnEnterKeyHandler(){
            @Override
            public void onEnterKeyPressed() {
                btnLogin.click();
            }
        };
        onEnterHandler.installOn(password);

        /// Copyright
        HorizontalLayout hlCopyrights = new HorizontalLayout();
            hlCopyrights.setHeightUndefined();
            hlCopyrights.setWidthUndefined();
        char copy = 169;

        //////////////////////////////////////////////// Add to content ////////////////////////////////////////////////
        addComponent(hlTop);
            hlTop.addComponent(labelBanner);
        addComponent(panel);
            panel.setContent(flayout);
                flayout.addComponent(tfUserName);
                flayout.addComponent(password);
                flayout.addComponent(btnLogin);
        addComponent(hlCopyrights);
            hlCopyrights.addComponent(new Label("Copyright " + copy + " kupchenkov.a.v 2019"));

        ///////////////////////////////////////////// Aligments ////////////////////////////////////////////////////////
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setComponentAlignment(hlCopyrights, Alignment.BOTTOM_CENTER);
        setComponentAlignment(hlTop, Alignment.TOP_CENTER);
        hlTop.setComponentAlignment(labelBanner, Alignment.TOP_CENTER);
        flayout.setComponentAlignment(btnLogin, Alignment.BOTTOM_RIGHT);
    }

    ///////////////////////////////////////////////////////////////////////////
    /// Authentication
    public Button.ClickListener loginClick = new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            flayout.removeComponent(resultLabel);
            flayout.addComponent(resultLabel);
            user =  new AuthenticationService().authenticate(tfUserName.getValue(), password.getValue());
            if (user == null) {
                resultLabel.setValue("Пользователь не найден ");
            } else {
                getUI().getNavigator().addView(UserView.NAME, new UserView(user));
                getUI().getNavigator().navigateTo(UserView.NAME);
            }
        }
    };

}
