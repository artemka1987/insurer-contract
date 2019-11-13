package ru.kupchenkov.view.user;

import com.vaadin.navigator.View;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import ru.kupchenkov.entity.User;
import ru.kupchenkov.resource.Images;

public class UserView extends VerticalLayout implements View {

    public static final String NAME = "user";

    public UserView(User user) {
        setSizeFull();
        setMargin(false);

        //Panel content
        Panel panelContent = new Panel(user.toString());
            panelContent.setWidth(100, Unit.PERCENTAGE);
            panelContent.setHeightUndefined();
            panelContent.addStyleName("arm-title");
            //Layout content
            VerticalLayout vlContent = new VerticalLayout();
                vlContent.setWidth(100, Unit.PERCENTAGE);
                vlContent.setHeightUndefined();


        /////////////////////////////////////////////////// Add to content /////////////////////////////////////////////
        addComponent(panelContent);
            panelContent.setContent(vlContent);
    }

}
