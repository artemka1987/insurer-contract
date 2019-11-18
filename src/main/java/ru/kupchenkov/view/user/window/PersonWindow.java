package ru.kupchenkov.view.user.window;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import ru.kupchenkov.additional.AdditionalUtils;
import ru.kupchenkov.dao.PersonDao;
import ru.kupchenkov.entity.Person;
import ru.kupchenkov.resource.Images;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class PersonWindow extends Window {

    private TextField tfFio = new TextField("Ф.И.О.");
    private Button btnSearch = new Button(null, Images.icoFind);
    private TextField tfLastName = new TextField("Фамилия");
    private TextField tfFirstName = new TextField("Имя");
    private TextField tfMiddleName = new TextField("Отчество");
    private DateField dfBirthDate = new DateField("Дата рождения");
    private TextField tfPassportSeries = new TextField("Серия паспорта");
    private TextField tfPassportNumber = new TextField("Номер пасспорта");
    private Grid<Person> grid = new Grid<>();
    private Button btnSave = new Button("Сохранить", Images.icoSave);
    private Person localPerson;
    private PersonDao personDao;
    private List<Person> personList;

    public PersonWindow(Person person, ContractWindow contractWindow) {
    this.localPerson = person;
    personDao = new PersonDao(AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager());

    setWidth(1400,Unit.PIXELS);
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
                            btnSearch.addClickListener(new Button.ClickListener() {
                                @Override
                                public void buttonClick(Button.ClickEvent clickEvent) {
                                    personList = personDao.findPersonsFromLikeFio(tfFio.getValue());
                                    if (personList.isEmpty()) Notification.show("Нет результатов", Notification.Type.WARNING_MESSAGE);
                                    grid.setItems(personList);
                                }
                            });
                        //Grid
                        grid.setWidth(100, Unit.PERCENTAGE);
                        grid.setRowHeight(40d);
                        grid.setHeightUndefined();
                        grid.setColumnReorderingAllowed(true);
                        grid.addColumn(Person::getFio).setCaption("Ф.И.О.");
                        grid.addColumn(Person::getBirthDateForGrid).setCaption("Дата рожд.").setWidth(140d);
                        grid.addColumn(Person::getPassport).setCaption("Документ").setWidth(160d);
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
                        //Layout passport
                        HorizontalLayout hlPassport = new HorizontalLayout();
                            hlPassport.setWidth(100, Unit.PERCENTAGE);
                            hlPassport.setHeightUndefined();
                            hlPassport.setMargin(false);
                            hlPassport.setSpacing(true);
                            //Df birth date
                            dfBirthDate.setPlaceholder("Дата рожд.");
                            dfBirthDate.setWidth(100, Unit.PERCENTAGE);
                            dfBirthDate.addStyleName("lable-group-caption");
                            dfBirthDate.setDateFormat("dd.MM.yyyy");
                            dfBirthDate.setParseErrorMessage("Неверный формат даты");
                            dfBirthDate.setRequiredIndicatorVisible(true);
                            //Tf series
                            tfPassportSeries.setWidth(100, Unit.PERCENTAGE);
                            tfPassportSeries.addStyleName("lable-tf-caption");
                            tfPassportSeries.setPlaceholder("Серия");
                            tfPassportSeries.setMaxLength(4);
                            tfPassportSeries.setRequiredIndicatorVisible(true);
                            //Tf number
                            tfPassportNumber.setWidth(100, Unit.PERCENTAGE);
                            tfPassportNumber.addStyleName("lable-tf-caption");
                            tfPassportNumber.setPlaceholder("Номер");
                            tfPassportNumber.setMaxLength(6);
                            tfPassportNumber.setRequiredIndicatorVisible(true);
                        //Button save
                        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
                        btnSave.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                String errors = "";
                                if (tfLastName.getValue().trim().length() == 0) errors += "\n Не заполнена фамилия";
                                if (tfFirstName.getValue().trim().length() == 0) errors += "\n Не заполнено имя";
                                if (dfBirthDate.getValue() == null) errors += "\n Не заполнена дата рождения";
                                if (tfPassportSeries.getValue().trim().length() == 0) errors += "\n Не заполнена серия паспорта";
                                if (tfPassportSeries.getValue().trim().length() < 4) errors += "\n Некорректно заполнена серия паспорта";
                                if (tfPassportNumber.getValue().trim().length() == 0) errors += "\n Не заполнен номер паспорта";
                                if (tfPassportNumber.getValue().trim().length() < 6 || !AdditionalUtils.isInteger(tfPassportNumber.getValue())) errors += "\n Некорректно заполнен номер паспорта";
                                if (errors.trim().length() == 0) {
                                    EntityManager manager = AdditionalUtils.getFactory(VaadinSession.getCurrent()).createEntityManager();
                                    manager.getTransaction().begin();
                                    try {
                                        PersonDao personDao = new PersonDao(manager);
                                        localPerson = new Person(tfLastName.getValue().trim(), tfFirstName.getValue().trim(),
                                                tfMiddleName.getValue().trim(), Date.valueOf(dfBirthDate.getValue()),
                                                tfPassportSeries.getValue(), Integer.valueOf(tfPassportNumber.getValue()));
                                        personDao.save(localPerson);
                                        manager.getTransaction().commit();
                                        Notification notification = new Notification("Данные успешно сохранены");
                                        notification.setPosition(Position.TOP_RIGHT);
                                        notification.show(Page.getCurrent());
                                        contractWindow.setPersonInfo(localPerson);
                                        close();
                                    } catch (Exception e) {
                                        manager.getTransaction().rollback();
                                        Notification.show("Ошибка сохранения: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
                                    }
                                } else {
                                    Notification.show(errors, Notification.Type.WARNING_MESSAGE);
                                }
                            }
                        });


        ///////////////////////////////////////////// Add to content ///////////////////////////////////////////////////
        setContent(panelContent);
            panelContent.setContent(hlContent);
                hlContent.addComponent(panelSearch);
                    panelSearch.setContent(vlSearch);
                        vlSearch.addComponent(hlSearchParams);
                            hlSearchParams.addComponent(tfFio);
                            hlSearchParams.addComponent(btnSearch);
                        vlSearch.addComponent(grid);
                hlContent.addComponent(panelPerson);
                    panelPerson.setContent(vlPerson);
                        vlPerson.addComponent(hlFio);
                            hlFio.addComponent(tfLastName);
                            hlFio.addComponent(tfFirstName);
                            hlFio.addComponent(tfMiddleName);
                        vlPerson.addComponent(hlPassport);
                            hlPassport.addComponent(dfBirthDate);
                            hlPassport.addComponent(tfPassportSeries);
                            hlPassport.addComponent(tfPassportNumber);
                        vlPerson.addComponent(btnSave);

        ///////////////////////////////////////////////////// Aligment /////////////////////////////////////////////////
        hlSearchParams.setExpandRatio(tfFio, 8f);
        hlSearchParams.setExpandRatio(btnSearch, 1f);
        hlSearchParams.setComponentAlignment(btnSearch, Alignment.BOTTOM_LEFT);
        vlPerson.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);
    }
}
