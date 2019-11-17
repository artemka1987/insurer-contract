package ru.kupchenkov.view.user.window;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.entity.Person;
import ru.kupchenkov.resource.Images;

public class PersonWindow extends Window {

    private TextField tfFio = new TextField("Ф.И.О.");
    private Button btnSearch = new Button(null, Images.icoFind);
    private TextField tfLastName = new TextField("Фамилия");
    private TextField tfFirstName = new TextField("Имя");
    private TextField tfMiddleName = new TextField("Отчество");
    private DateField dfBirthDate = new DateField("Дата рождения");
    private TextField tfPassportSeries = new TextField("Серия паспорта");
    private TextField tfPassportNumber = new TextField("Номер пасспорта");

    public PersonWindow(Person person) {

    setWidth(1100,Unit.PIXELS);
    setHeightUndefined();
    setModal(true);
    setResizable(false);
    center();

    //Panel content
    Panel panelContent = new Panel("Страхователь");
            panelContent.setWidth(100,Unit.PERCENTAGE);
            panelContent.setHeightUndefined();
            panelContent.setIcon(Images.icoUser);
            panelContent.addStyleName("arm-title");
            //Layout content
            HorizontalLayout hlContent = new HorizontalLayout();
                hlContent.setWidth(100,Unit.PERCENTAGE);
                hlContent.setHeightUndefined();
                hlContent.setMargin(true);
                hlContent.setSpacing(true);
                //Panel search
                Panel panelSearch = new Panel("Поиск");
                    panelSearch.setWidth(100, Unit.PERCENTAGE);
                    panelSearch.setHeightUndefined();
                    panelSearch.setIcon(Images.icoFind);
                    panelSearch.addStyleName("arm-title-violet");
                    //Layout search
                    VerticalLayout vlSearch = new VerticalLayout();
                        vlSearch.setWidth(100, Unit.PERCENTAGE);
                        vlSearch.setHeightUndefined();
                        vlSearch.setMargin(true);
                        vlSearch.setSpacing(true);
                        //Layout serach params
                        HorizontalLayout hlSearchParams = new HorizontalLayout();
                            hlSearchParams.setWidth(100, Unit.PERCENTAGE);
                            hlSearchParams.setHeightUndefined();
                            hlSearchParams.setMargin(false);
                            hlSearchParams.setSpacing(true);
                            //Tf fio
                            tfFio.setWidth(100, Unit.PERCENTAGE);
                            tfFio.addStyleName("lable-tf-caption");
                            tfFio.setPlaceholder("Укажите Ф.И.О. полностью или частично");
                            //Button search
                            btnSearch.setStyleName(ValoTheme.BUTTON_FRIENDLY);
                            btnSearch.setWidth(100, Unit.PERCENTAGE);
                //Panel person
                Panel panelPerson = new Panel("Страхователь");
                    panelPerson.setWidth(100, Unit.PERCENTAGE);
                    panelPerson.setHeightUndefined();
                    panelPerson.addStyleName("arm-title-violet");
                    panelPerson.setIcon(Images.icoUser);
                    //Layout person
                    VerticalLayout vlPerson = new VerticalLayout();
                        vlPerson.setWidth(100, Unit.PERCENTAGE);
                        vlPerson.setHeightUndefined();
                        vlPerson.setMargin(true);
                        vlPerson.setSpacing(true);
                        //Layout fio
                        HorizontalLayout hlFio = new HorizontalLayout();
                            hlFio.setWidth(100, Unit.PERCENTAGE);
                            hlFio.setHeightUndefined();
                            hlFio.setMargin(false);
                            hlFio.setSpacing(true);
                            //Tf lastname
                            tfLastName.setWidth(100, Unit.PERCENTAGE);
                            tfLastName.addStyleName("lable-tf-caption");
                            tfLastName.setPlaceholder("фамилия");
                            tfLastName.setMaxLength(100);
                            tfLastName.setRequiredIndicatorVisible(true);
                            //Tf firstname
                            tfFirstName.setWidth(100, Unit.PERCENTAGE);
                            tfFirstName.addStyleName("lable-tf-caption");
                            tfFirstName.setPlaceholder("имя");
                            tfFirstName.setMaxLength(100);
                            tfFirstName.setRequiredIndicatorVisible(true);
                            //Tf middlename
                            tfMiddleName.setWidth(100, Unit.PERCENTAGE);
                            tfMiddleName.addStyleName("lable-tf-caption");
                            tfMiddleName.setPlaceholder("отчество");
                            tfMiddleName.setMaxLength(100);


        ///////////////////////////////////////////// Add to content ///////////////////////////////////////////////////
        setContent(panelContent);
            panelContent.setContent(hlContent);
                hlContent.addComponent(panelSearch);
                    panelSearch.setContent(vlSearch);
                        vlSearch.addComponent(hlSearchParams);
                            hlSearchParams.addComponent(tfFio);
                            hlSearchParams.addComponent(btnSearch);
                hlContent.addComponent(panelPerson);
                    panelPerson.setContent(vlPerson);
                        vlPerson.addComponent(hlFio);
                            hlFio.addComponent(tfLastName);
                            hlFio.addComponent(tfFirstName);
                            hlFio.addComponent(tfMiddleName);

        ///////////////////////////////////////////////////// Aligment /////////////////////////////////////////////////
        hlSearchParams.setExpandRatio(tfFio, 8f);
        hlSearchParams.setExpandRatio(btnSearch, 1f);
        hlSearchParams.setComponentAlignment(btnSearch, Alignment.BOTTOM_LEFT);
    }
}
